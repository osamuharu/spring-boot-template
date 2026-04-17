package com.osamuharu.core.config;

import com.osamuharu.core.properties.ApiProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.ApiVersionConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final ApiProperties apiProperties;

  private String[] supportedVersions;

  @PostConstruct
  public void init() {
    this.supportedVersions = apiProperties.getSupportedVersions().toArray(String[]::new);
  }

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.addPathPrefix(apiProperties.getPrefix(),
        HandlerTypePredicate.forAnnotation(RequestMapping.class));
  }

  @Override
  public void configureApiVersioning(ApiVersionConfigurer configurer) {
    configurer.usePathSegment(1)
        .addSupportedVersions(supportedVersions)
        .setDefaultVersion(apiProperties.getDefaultVersion());
  }
}
