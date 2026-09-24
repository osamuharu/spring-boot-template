package com.osamuharu.auth.application.config;

import com.osamuharu.auth.application.useCases.LoginUseCase;
import com.osamuharu.auth.application.useCases.LogoutUseCase;
import com.osamuharu.auth.application.useCases.RegisterUseCase;
import com.osamuharu.security.ports.BlackListPort;
import com.osamuharu.security.ports.InternalSecurityPort;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.security.ports.UserCredentialsPort;
import com.osamuharu.user.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthConfiguration {

  private final UserService userService;
  private final InternalSecurityPort internalSecurityPort;
  private final TokenPort tokenPort;
  private final BlackListPort blackListPort;
  private final ApplicationEventPublisher eventPublisher;
  private final UserCredentialsPort userCredentialsPort;

  @Bean
  public RegisterUseCase registerUseCase() {
    return new RegisterUseCase(userService, eventPublisher);
  }

  @Bean
  public LoginUseCase loginUseCase() {
    return new LoginUseCase(internalSecurityPort, userCredentialsPort);
  }

  @Bean
  public LogoutUseCase logoutUseCase() {
    return new LogoutUseCase(tokenPort, blackListPort);
  }
}
