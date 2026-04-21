package com.osamuharu.user.infrastructure.persistence.adapter;

import com.osamuharu.shared.dto.UserSecurityDto;
import com.osamuharu.shared.port.UserSecurityPort;
import com.osamuharu.user.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSecurityAdapter implements UserSecurityPort {

  private final UserRepository userRepository;

  @Override
  public Optional<UserSecurityDto> loadUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(user -> UserSecurityDto.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .build());
  }
}