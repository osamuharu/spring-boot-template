package com.osamuharu.auth.application.config;

import com.osamuharu.auth.application.usecase.LoginUseCase;
import com.osamuharu.auth.application.usecase.LogoutUseCase;
import com.osamuharu.auth.application.usecase.RegisterUseCase;
import com.osamuharu.shared.provider.MemoryProvider;
import com.osamuharu.shared.provider.PasswordProvider;
import com.osamuharu.shared.provider.TokenProvider;
import com.osamuharu.user.application.usecase.CreateUserUseCase;
import com.osamuharu.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration {

  private final CreateUserUseCase createUserUseCase;
  private final UserRepository userRepository;
  private final PasswordProvider passwordProvider;
  private final TokenProvider tokenProvider;
  private final MemoryProvider memoryProvider;

  @Bean
  public RegisterUseCase registerUseCase() {
    return new RegisterUseCase(createUserUseCase);
  }

  @Bean
  public LoginUseCase loginUseCase() {
    return new LoginUseCase(userRepository, passwordProvider);
  }

  @Bean
  public LogoutUseCase logoutUseCase() {
    return new LogoutUseCase(tokenProvider, memoryProvider);
  }
}
