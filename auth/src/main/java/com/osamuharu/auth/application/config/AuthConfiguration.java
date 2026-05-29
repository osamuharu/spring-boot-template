package com.osamuharu.auth.application.config;

import com.osamuharu.auth.application.useCases.LoginUseCase;
import com.osamuharu.auth.application.useCases.LogoutUseCase;
import com.osamuharu.auth.application.useCases.RegisterUseCase;
import com.osamuharu.security.ports.BlackListPort;
import com.osamuharu.security.ports.PasswordPort;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration {

  private final UserRepository userRepository;
  private final PasswordPort passwordPort;
  private final TokenPort tokenPost;
  private final BlackListPort blackListPort;

  @Bean
  public RegisterUseCase registerUseCase() {
    return new RegisterUseCase(userRepository, passwordPort);
  }

  @Bean
  public LoginUseCase loginUseCase() {
    return new LoginUseCase(userRepository, passwordPort);
  }

  @Bean
  public LogoutUseCase logoutUseCase() {
    return new LogoutUseCase(tokenPost, blackListPort);
  }
}
