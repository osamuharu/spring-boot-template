package com.osamuharu.user.infrastructure.persistence.adapters;

import com.osamuharu.security.dtos.UserCredentialsDto;
import com.osamuharu.security.ports.UserCredentialsPort;
import com.osamuharu.user.domain.repositories.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCredentialsAdapter implements UserCredentialsPort {

  private final UserRepository userRepository;

  @Override
  public Optional<UserCredentialsDto> loadUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(user -> UserCredentialsDto.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build());
  }
}