package com.osamuharu.file.infrastructure.cloudinary;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "cloudinary")
@Validated
public class CloudinaryProperties {

  @NotNull(message = "Cloud name must not be null")
  private String cloudName;

  @NotNull(message = "API key must not be null")
  private String apiKey;

  @NotNull(message = "API secret must not be null")
  private String apiSecret;
}
