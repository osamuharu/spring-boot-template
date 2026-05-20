package com.osamuharu.auth.application.useCases;

import com.osamuharu.auth.presentation.dto.requests.LoginRequestDto;
import com.osamuharu.security.ports.PasswordPort;
import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUseCase {

  private final UserRepository userRepository;
  private final PasswordPort passwordPort;

  public User execute(LoginRequestDto dto) {
    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordPort.verifyPassword(dto.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid password");
    }

    return user;
  }
}
