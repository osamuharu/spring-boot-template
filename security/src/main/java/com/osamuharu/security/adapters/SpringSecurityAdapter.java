package com.osamuharu.security.adapters;

import com.osamuharu.security.ports.InternalSecurityPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringSecurityAdapter implements InternalSecurityPort {

  private final HttpServletRequest request;

  @Override
  public void setContextAsUser(UserDetails userDetails, String token) {
    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, token,
            userDetails.getAuthorities());

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Override
  public String getCurrentToken() {
    {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication == null || !authentication.isAuthenticated()) {
        return null;
      }

      if (authentication.getCredentials() instanceof String token && !token.isBlank()) {
        return token;
      }

      return null;
    }
  }

  @Override
  public UserDetails getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }

    if (authentication.getPrincipal() instanceof UserDetails userDetails) {
      return userDetails;
    }

    return null;
  }
}
