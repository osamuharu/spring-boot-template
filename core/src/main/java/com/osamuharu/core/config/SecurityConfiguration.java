package com.osamuharu.core.config;

import com.osamuharu.core.filter.ExceptionFilter;
import com.osamuharu.core.filter.JwtFilter;
import com.osamuharu.core.properties.SecurityProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

  private final SecurityProperties properties;
  private final UserDetailsService userDetailsService;
  private final AuthenticationEntryPoint authenticationEntryPoint;
  private final ExceptionFilter exceptionFilter;
  private final JwtFilter jwtFilter;

  private String[] publicUrls;
  private static final int BCRYPT_STRENGTH = 12;

  @PostConstruct
  public void init() {
    this.publicUrls = properties.getPublicUrls().toArray(String[]::new);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
    return config.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) {

    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(publicUrls)
            .permitAll()
            .anyRequest()
            .authenticated()
        )
        .authenticationProvider(authenticationProvider())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(
            exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
        .addFilterBefore(exceptionFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterAfter(jwtFilter, ExceptionFilter.class);

    return http.build();
  }

}
