package com.osamuharu.shared.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UrlDto {

  @NotBlank(message = "Protocol must not be blank")
  private String protocol;

  @NotBlank(message = "Host must not be blank")
  private String host;

  private Integer port;
  private String description;

  @Override
  public String toString() {
    if (port != null) {
      return String.format("%s://%s:%d", protocol, host, port);
    }
    return String.format("%s://%s", protocol, host);
  }
}
