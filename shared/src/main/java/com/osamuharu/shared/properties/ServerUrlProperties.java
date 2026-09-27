package com.osamuharu.shared.properties;

import com.osamuharu.shared.dtos.UrlDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.server")
@Validated
public class ServerUrlProperties {

  @NotEmpty(message = "Server URLs cannot be empty")
  private List<@Valid UrlDto> urls;
}
