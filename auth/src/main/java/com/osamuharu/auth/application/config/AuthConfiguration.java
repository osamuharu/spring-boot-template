package com.osamuharu.auth.application.config;

import com.osamuharu.auth.application.usecase.LoginUseCase;
import com.osamuharu.auth.application.usecase.LogoutUseCase;
import com.osamuharu.auth.application.usecase.RegisterUseCase;
import com.osamuharu.security.port.BlackListPort;
import com.osamuharu.security.port.PasswordPort;
import com.osamuharu.security.port.TokenPort;
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
  private final PasswordPort passwordPort;
  private final TokenPort tokenPost;
  private final BlackListPort blackListPort;

  @Bean
  public RegisterUseCase registerUseCase() {
    return new RegisterUseCase(createUserUseCase);
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
