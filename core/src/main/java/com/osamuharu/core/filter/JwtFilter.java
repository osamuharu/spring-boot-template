package com.osamuharu.core.filter;

import com.osamuharu.core.properties.SecurityProperties;
import com.osamuharu.shared.provider.TokenProvider;
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
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final SecurityProperties securityProperties;
  private final UserDetailsService userDetailsService;
  private final TokenProvider tokenProvider;
  private final PathPatternParser pathPatternParser;
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

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }
    return null;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws JwtException, ServletException,
      IOException, UsernameNotFoundException {

    String token = parseJwt(request);

    if (token != null) {
      if (!tokenProvider.validateToken(token)) {
        throw new JwtException("Invalid JWT token");
      }

      String username = tokenProvider.extractUsername(token);

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
