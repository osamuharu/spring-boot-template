package com.osamuharu.core.mailer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.mail")
@Validated
public class MailerProperties {

  @Email(message = "Invalid email format for mail username")
  @NotBlank(message = "Mail username must not be blank")
  private String username;
}
