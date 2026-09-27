package com.osamuharu.auth.application.config;

import com.osamuharu.auth.application.useCases.LoginUseCase;
import com.osamuharu.auth.application.useCases.LogoutUseCase;
import com.osamuharu.auth.application.useCases.RegisterUseCase;
import com.osamuharu.security.ports.BlackListPort;
import com.osamuharu.security.ports.InternalSecurityPort;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.security.ports.UserCredentialsPort;
import com.osamuharu.user.application.properties.UserProperties;
import com.osamuharu.user.application.services.UserService;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.MediaType;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
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

  @Bean
  public OperationCustomizer loginExamplesCustomizer(UserProperties userProperties) {
    return (operation, handlerMethod) -> {
      if ("login".equals(handlerMethod.getMethod().getName())
          && operation.getRequestBody() != null) {
        MediaType mediaType = operation.getRequestBody().getContent().get("application/json");

        if (mediaType != null && userProperties.getDefaultUsers() != null) {
          userProperties.getDefaultUsers().forEach(user -> {
            Example example = new Example()
                .summary("Account: " + user.getUsername())
                .value(Map.of(
                    "email", user.getEmail(),
                    "password", user.getPassword()
                ));

            mediaType.addExamples(user.getUsername(), example);
          });
        }
      }
      return operation;
    };
  }
}
