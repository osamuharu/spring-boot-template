package com.osamuharu.auth.application.useCases;

import com.osamuharu.user.application.useCases.CreateUserUseCase;
import com.osamuharu.user.domain.entities.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUseCase {

  private final CreateUserUseCase createUserUseCase;

  public void execute(User user) {
    createUserUseCase.execute(user);
  }
}
