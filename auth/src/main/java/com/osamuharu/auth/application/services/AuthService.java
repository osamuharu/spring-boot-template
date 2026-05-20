package com.osamuharu.auth.application.services;

import com.osamuharu.auth.application.mappers.AuthMapper;
import com.osamuharu.auth.application.useCases.LoginUseCase;
import com.osamuharu.auth.application.useCases.LogoutUseCase;
import com.osamuharu.auth.application.useCases.RegisterUseCase;
import com.osamuharu.auth.presentation.dto.requests.LoginRequestDto;
import com.osamuharu.auth.presentation.dto.requests.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.responses.LoginResponseDto;
import com.osamuharu.security.dtos.PayloadDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.shared.dtos.SendMailMessageSimpleDto;
import com.osamuharu.user.domain.entities.User;
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
