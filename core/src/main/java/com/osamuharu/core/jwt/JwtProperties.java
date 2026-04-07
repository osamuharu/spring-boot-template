package com.osamuharu.core.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("classpath:core-dev.properties")
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

  private String secret;
  private long accessTokenExpire;
}
