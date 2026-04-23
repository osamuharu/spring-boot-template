package com.osamuharu.core.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.api")
@Validated
public class ApiProperties {

  @NotBlank(message = "API prefix must not be blank")
  private String prefix = "/api";

  @NotBlank(message = "Default API version must not be blank")
  private String defaultVersion = "v1";

  @NotEmpty(message = "Supported API versions cannot be empty")
  private List<
      @NotBlank
      @Pattern(regexp = "^v\\d+$", message = "Each API version must start with 'v' followed by a number")
          String
      > supportedVersions;
}
