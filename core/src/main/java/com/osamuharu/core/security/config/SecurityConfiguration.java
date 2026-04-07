package com.osamuharu.core.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.stream.Stream;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
	final String[] PUBLIC_ENDPOINTS = {"/api/{version}/auth/**", "/api/{version}/public/**"};
	final String[] SWAGGER_ENDPOINTS = {"/v3/api-docs", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		final String[] COMBINED_ENDPOINTS = Stream.concat(
				                                          Arrays.stream(PUBLIC_ENDPOINTS),
				                                          Arrays.stream(SWAGGER_ENDPOINTS))
		                                          .toArray(String[]::new);
		http
				.csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers(COMBINED_ENDPOINTS)
						.permitAll()
						.anyRequest()
						.authenticated()
				);
		
		return http.build();
	}
	
}
