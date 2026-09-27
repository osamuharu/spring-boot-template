package com.osamuharu.shared.config;


import com.osamuharu.shared.properties.ServerUrlProperties;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfiguration {

  private final BuildProperties buildProperties;
  private final ServerUrlProperties serverUrlProperties;

  @Bean
  public OpenAPI customOpenAPI() {
    final String appVersion = buildProperties.getVersion();

    return new OpenAPI()
        .info(new Info()
            .title("Template System API")
            .version(appVersion)
            .description("Document API for system template")
            .contact(new Contact()
                .name("osamuharu")
                .email("huynhnamkha1010@gmail.com")
                .url("https://github.com/osamuharu/spring-boot-template"))
            .license(new License()
                .name("MIT License")
                .url("https://opensource.org/license/mit"))

        )
        .servers(serverUrlProperties.getUrls().stream()
            .map(urlDto -> new Server().url(urlDto.toString()).description(urlDto.getDescription()))
            .toList());
  }
}
