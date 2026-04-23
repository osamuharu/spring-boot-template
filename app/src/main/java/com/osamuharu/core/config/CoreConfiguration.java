package com.osamuharu.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.pattern.PathPatternParser;

@Configuration
public class CoreConfiguration {

  @Bean
  public PathPatternParser pathPatternParser() {
    return new PathPatternParser();
  }
}
