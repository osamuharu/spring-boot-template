package com.osamuharu.auth.application.useCases;

import com.osamuharu.user.application.services.UserService;
import com.osamuharu.user.presentation.dto.requests.CreateUserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUseCase {

  private final UserService userService;

  public void execute(CreateUserDto dto) {
    userService.createUser(dto);
  }
}
