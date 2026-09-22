package com.osamuharu.auth.application.useCases;

import com.osamuharu.shared.dtos.SendMailMessageSimpleDto;
import com.osamuharu.user.application.services.UserService;
import com.osamuharu.user.presentation.dto.requests.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class RegisterUseCase {

  private final UserService userService;
  private final ApplicationEventPublisher eventPublisher;

  public void execute(CreateUserDto dto) {
    userService.createUser(dto);

    eventPublisher.publishEvent(
        SendMailMessageSimpleDto.builder()
            .to(dto.getEmail())
            .subject("Welcome " + dto.getUsername())
            .text("Welcome to our service, " + dto.getUsername() + "!")
            .build()
    );
  }
}
