package com.osamuharu.security.filters;

import com.osamuharu.security.ports.BlackListPort;
import com.osamuharu.security.ports.InternalSecurityPort;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.shared.utils.TokenUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

  private final TokenPort tokenPort;
  private final BlackListPort blackListPort;
  private final InternalSecurityPort internalSecurityPort;

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    return TokenUtils.extractTokenFromHeader(headerAuth);
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws JwtException, ServletException,
      IOException, UsernameNotFoundException {

    String token = parseJwt(request);

    if (token != null && tokenPort.isValidToken(token)) {

      String idToken = tokenPort.extractIdToken(token);

      if (idToken != null && blackListPort.hasToken(idToken)) {
        throw new JwtException("Token is revoked");
      }

      String username = tokenPort.extractPayload(token).getUsername();

      internalSecurityPort.setContextAsUser(username);
    }

    filterChain.doFilter(request, response);
  }
}
