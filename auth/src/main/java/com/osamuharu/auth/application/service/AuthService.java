package com.osamuharu.auth.application.service;

import com.osamuharu.auth.application.mapper.AuthMapper;
import com.osamuharu.auth.application.usecase.LoginUseCase;
import com.osamuharu.auth.application.usecase.RegisterUseCase;
import com.osamuharu.auth.presentation.dto.request.LoginRequestDto;
import com.osamuharu.auth.presentation.dto.request.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.response.LoginResponseDto;
import com.osamuharu.shared.entity.Subject;
import com.osamuharu.shared.entity.Token;
import com.osamuharu.shared.provider.TokenProvider;
import com.osamuharu.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final RegisterUseCase registerUseCase;
  private final LoginUseCase loginUseCase;
  private final AuthMapper mapper;
  private final TokenProvider tokenProvider;

  public void register(RegisterRequestDto dto) {
    registerUseCase.execute(mapper.toDomain(dto));
  }

  public LoginResponseDto login(LoginRequestDto dto) {
    User user = loginUseCase.execute(dto);

    Subject subject = Subject.builder()
        .username(user.getUsername())
        .build();

    Token accessToken = tokenProvider.generateAccessToken(subject);

    return mapper.toDto(user, accessToken, "Bearer");
  }
}
