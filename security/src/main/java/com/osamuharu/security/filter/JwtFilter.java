package com.osamuharu.security.filter;

import com.osamuharu.security.port.BlackListPort;
import com.osamuharu.security.port.TokenPort;
import com.osamuharu.security.properties.SecurityProperties;
import com.osamuharu.shared.utils.TokenUtils;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.PathContainer;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final SecurityProperties securityProperties;
  private final UserDetailsService userDetailsService;
  private final TokenPort tokenPort;
  private final PathPatternParser pathPatternParser;
  private final BlackListPort blackListPort;
  private List<PathPattern> publicPatterns;

  @PostConstruct
  public void init() {
    this.publicPatterns = securityProperties.getPublicUrls().stream()
        .map(pathPatternParser::parse)
        .toList();
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String pathStr = request.getServletPath();

    PathContainer path = PathContainer.parsePath(pathStr);

    return publicPatterns.stream().anyMatch(pattern -> pattern.matches(path));
  }

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

    if (token != null) {
      String idToken = tokenPort.extractIdToken(token);

      if (idToken != null && blackListPort.hasToken(idToken)) {
        throw new JwtException("Token is revoked");
      }

      String username = tokenPort.extractPayload(token).getUsername();

      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContextHolder.getContext()
          .setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
