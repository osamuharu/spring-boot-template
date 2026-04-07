package com.osamuharu.core.security.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {
	
	private final BuildProperties buildProperties;
	
	
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
				);
	}
}
