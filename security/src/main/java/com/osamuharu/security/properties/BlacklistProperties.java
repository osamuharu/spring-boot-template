package com.osamuharu.security.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.blacklist")
@Validated
public class BlacklistProperties {

  @NotBlank
  @Pattern(
      regexp = "^([a-zA-Z0-9_-]+:)+$",
      message = "Token prefix is invalid. It must consist of alphanumeric characters, hyphens, or "
          + "underscores, separated by a single colon, and MUST end with a colon "
          + "(e.g., 'blacklist:token:')"
  )
  private String redisTokenPrefix = "blacklist:token:";
}
