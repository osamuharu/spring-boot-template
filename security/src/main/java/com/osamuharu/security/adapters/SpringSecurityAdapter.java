package com.osamuharu.security.adapters;

import com.osamuharu.security.ports.InternalSecurityPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringSecurityAdapter implements InternalSecurityPort {

  private final UserDetailsService userDetailsService;
  private final HttpServletRequest request;

  @Override
  public void setContextAsUser(String username) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

    UsernamePasswordAuthenticationToken authentication =
        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Override
  public Authentication getCurrentAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }
}
