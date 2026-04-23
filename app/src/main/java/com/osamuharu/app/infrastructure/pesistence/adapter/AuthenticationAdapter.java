package com.osamuharu.app.infrastructure.pesistence.adapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationAdapter implements AuthenticationEntryPoint {

  @Override
  public void commence(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull AuthenticationException authException) {
    throw authException;
  }
}
