package com.osamuharu.user.application.config;

import com.osamuharu.security.ports.PasswordPort;
import com.osamuharu.user.application.useCases.CreateUserUseCase;
import com.osamuharu.user.application.useCases.DeleteUseUseCase;
import com.osamuharu.user.application.useCases.UpdateUserUseCase;
import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {

  private final UserRepository userRepository;
  private final PasswordPort passwordPort;

  @Bean
  CreateUserUseCase createUserUseCase() {
    return new CreateUserUseCase(userRepository, passwordPort);
  }

  @Bean
  UpdateUserUseCase updateUserUseCase() {
    return new UpdateUserUseCase(userRepository, passwordPort);
  }

  @Bean
  DeleteUseUseCase deleteUseUseCase() {
    return new DeleteUseUseCase(userRepository);
  }
}
