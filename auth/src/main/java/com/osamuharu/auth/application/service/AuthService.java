package com.osamuharu.auth.application.service;

import com.osamuharu.auth.application.mapper.AuthMapper;
import com.osamuharu.auth.application.usecase.LoginUseCase;
import com.osamuharu.auth.application.usecase.LogoutUseCase;
import com.osamuharu.auth.application.usecase.RegisterUseCase;
import com.osamuharu.auth.presentation.dto.request.LoginRequestDto;
import com.osamuharu.auth.presentation.dto.request.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.response.LoginResponseDto;
import com.osamuharu.shared.dto.PayloadDto;
import com.osamuharu.shared.dto.TokenDto;
import com.osamuharu.shared.port.TokenPost;
import com.osamuharu.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final RegisterUseCase registerUseCase;
  private final LoginUseCase loginUseCase;
  private final LogoutUseCase logoutUseCase;
  private final AuthMapper mapper;
  private final TokenPost tokenPost;

  public void register(RegisterRequestDto dto) {
    registerUseCase.execute(mapper.toDomain(dto));
  }

  public LoginResponseDto login(LoginRequestDto dto) {
    User user = loginUseCase.execute(dto);

    PayloadDto payloadDto = PayloadDto.builder()
        .username(user.getUsername())
        .build();

    TokenDto accessTokenDto = tokenPost.generateAccessToken(payloadDto);

    return mapper.toDto(user, accessTokenDto, "Bearer");
  }

  public void logout(String token) throws IllegalAccessException {
    logoutUseCase.execute(token);
  }
}
