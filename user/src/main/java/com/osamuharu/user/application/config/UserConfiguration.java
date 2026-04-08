package com.osamuharu.user.application.config;

import com.osamuharu.shared.provider.PasswordProvider;
import com.osamuharu.user.application.usecase.CreateUserUseCase;
import com.osamuharu.user.application.usecase.DeleteUseUseCase;
import com.osamuharu.user.application.usecase.UpdateUserUseCase;
import com.osamuharu.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {

  private final UserRepository userRepository;
  private final PasswordProvider passwordProvider;

  @Bean
  CreateUserUseCase createUserUseCase() {
    return new CreateUserUseCase(userRepository, passwordProvider);
  }

  @Bean
  UpdateUserUseCase updateUserUseCase() {
    return new UpdateUserUseCase(userRepository, passwordProvider);
  }

  @Bean
  DeleteUseUseCase deleteUseUseCase() {
    return new DeleteUseUseCase(userRepository);
  }
}
