package com.osamuharu.auth.application.service;

import com.osamuharu.auth.application.mapper.AuthMapper;
import com.osamuharu.auth.application.usecase.LoginUseCase;
import com.osamuharu.auth.application.usecase.LogoutUseCase;
import com.osamuharu.auth.application.usecase.RegisterUseCase;
import com.osamuharu.auth.presentation.dto.request.LoginRequestDto;
import com.osamuharu.auth.presentation.dto.request.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.response.LoginResponseDto;
import com.osamuharu.security.dto.PayloadDto;
import com.osamuharu.security.dto.TokenDto;
import com.osamuharu.security.port.TokenPort;
import com.osamuharu.shared.dto.SendMailMessageSimpleDto;
import com.osamuharu.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
  private final ApplicationEventPublisher eventPublisher;

  @Transactional
  public void register(RegisterRequestDto dto) {

    registerUseCase.execute(mapper.toDomain(dto));

    eventPublisher.publishEvent(
        SendMailMessageSimpleDto.builder()
            .to(dto.getEmail())
            .subject("Welcome " + dto.getUsername())
            .text("Welcome to our service, " + dto.getUsername() + "!")
            .build()
    );
  }

  public LoginResponseDto login(LoginRequestDto dto) {
    User user = loginUseCase.execute(dto);

    PayloadDto payloadDto = PayloadDto.builder()
        .username(user.getUsername())
        .build();

    TokenDto accessTokenDto = tokenPort.generateAccessToken(payloadDto);

    return mapper.toDto(user, accessTokenDto, "Bearer");
  }

  public void logout(String token) throws IllegalAccessException {
    logoutUseCase.execute(token);
  }
}
