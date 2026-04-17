package com.osamuharu.core.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.security")
@Validated
public class SecurityProperties {

  @NotNull(message = "Public URLs cannot be null")
  @NotEmpty(message = "Public URLs cannot be empty")
  private List<
      @NotBlank(message = "Each URL element cannot be blank")
      @Pattern.List({
          @Pattern(regexp = "^/.*", message = "Each public URL must start with '/'"),
          @Pattern(regexp = "^/(?!.*\\s).*$", message = "Public URLs cannot contain whitespace")
      })
          String
      > publicUrls;
}
