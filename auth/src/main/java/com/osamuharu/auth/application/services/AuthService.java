package com.osamuharu.auth.application.services;

import com.osamuharu.auth.application.mappers.AuthMapper;
import com.osamuharu.auth.application.useCases.LoginUseCase;
import com.osamuharu.auth.application.useCases.LogoutUseCase;
import com.osamuharu.auth.application.useCases.RegisterUseCase;
import com.osamuharu.auth.presentation.dto.requests.LoginRequestDto;
import com.osamuharu.auth.presentation.dto.requests.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.responses.LoginResponseDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.security.ports.InternalSecurityPort;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.shared.dtos.UserDto;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final RegisterUseCase registerUseCase;
  private final LoginUseCase loginUseCase;
  private final LogoutUseCase logoutUseCase;
  private final AuthMapper mapper;
  private final TokenPort tokenPort;
  private final InternalSecurityPort internalSecurityPort;

  @Transactional
  public void register(RegisterRequestDto dto) {

    registerUseCase.execute(mapper.toDto(dto));
  }

  public LoginResponseDto login(LoginRequestDto dto) throws UserPrincipalNotFoundException {
    UserDto user = loginUseCase.execute(dto);

    Authentication authentication = internalSecurityPort.getCurrentAuthentication();

    TokenDto accessTokenDto = tokenPort.generateAccessToken(authentication);

    return mapper.toDto(user, accessTokenDto, "Bearer");
  }

  public void logout(String token) {
    logoutUseCase.execute(token);
  }
}
