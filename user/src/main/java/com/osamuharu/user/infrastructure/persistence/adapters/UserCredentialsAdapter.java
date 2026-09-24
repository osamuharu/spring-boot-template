package com.osamuharu.user.infrastructure.persistence.adapters;

import com.osamuharu.security.ports.UserCredentialsPort;
import com.osamuharu.shared.dtos.UserDto;
import com.osamuharu.user.application.mappers.UserAppMapper;
import com.osamuharu.user.domain.repositories.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCredentialsAdapter implements UserCredentialsPort {

  private final UserRepository userRepository;
  private final UserAppMapper userAppMapper;

  @Override
  public Optional<UserDto> loadUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .map(userAppMapper::toDto);
  }

  @Override
  public Optional<UserDto> loadUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .map(userAppMapper::toDto);
  }
}