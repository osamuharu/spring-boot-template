package com.osamuharu.auth.application.services;

import com.osamuharu.auth.application.mappers.AuthMapper;
import com.osamuharu.auth.application.useCases.LoginUseCase;
import com.osamuharu.auth.application.useCases.LogoutUseCase;
import com.osamuharu.auth.application.useCases.RegisterUseCase;
import com.osamuharu.auth.presentation.dto.requests.LoginRequestDto;
import com.osamuharu.auth.presentation.dto.requests.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.responses.LoginResponseDto;
import com.osamuharu.security.dtos.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final RegisterUseCase registerUseCase;
  private final LoginUseCase loginUseCase;
  private final LogoutUseCase logoutUseCase;
  private final AuthMapper mapper;

  @Transactional
  public void register(RegisterRequestDto dto) {
    registerUseCase.execute(mapper.toDto(dto));
  }

  public LoginResponseDto login(LoginRequestDto dto) {
    TokenDto accessTokenDto = loginUseCase.execute(dto);

    return mapper.toDto(accessTokenDto, "Bearer");
  }

  public void logout() {
    logoutUseCase.execute();
  }
}
