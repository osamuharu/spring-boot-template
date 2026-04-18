package com.osamuharu.core.jwt;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationFormat;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.jwt")
@Validated
public class JwtProperties {

  @NotBlank(message = "JWT secret must not be blank")
  @Pattern(
      regexp = "^[0-9a-fA-F]{64}$",
      message = "JWT secret must be a 64-character hexadecimal string (256 bits)"
  )
  private String secret;

  @NotNull(message = "Access token expiration time must not be null")
  @DurationFormat(DurationStyle.SIMPLE)
  private Duration accessTokenExpire;
}
