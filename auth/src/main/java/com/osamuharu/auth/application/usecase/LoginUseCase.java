package com.osamuharu.auth.application.usecase;

import com.osamuharu.auth.presentation.dto.request.LoginRequestDto;
import com.osamuharu.shared.port.PasswordPost;
import com.osamuharu.user.domain.entity.User;
import com.osamuharu.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUseCase {

  private final UserRepository userRepository;
  private final PasswordPost passwordPost;

  public User execute(LoginRequestDto dto) {
    User user = userRepository.findByEmail(dto.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordPost.verifyPassword(dto.getPassword(), user.getPassword())) {
      throw new RuntimeException("Invalid password");
    }

    return user;
  }
}
