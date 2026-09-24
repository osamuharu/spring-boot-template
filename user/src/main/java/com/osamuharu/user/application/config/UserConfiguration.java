package com.osamuharu.user.application.config;

import com.osamuharu.user.application.useCases.CreateUserUseCase;
import com.osamuharu.user.application.useCases.DeleteUseUseCase;
import com.osamuharu.user.application.useCases.UpdateUserUseCase;
import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Bean
  CreateUserUseCase createUserUseCase() {
    return new CreateUserUseCase(userRepository, passwordEncoder);
  }

  @Bean
  UpdateUserUseCase updateUserUseCase() {
    return new UpdateUserUseCase(userRepository, passwordEncoder);
  }

  @Bean
  DeleteUseUseCase deleteUseUseCase() {
    return new DeleteUseUseCase(userRepository);
  }
}
