package com.osamuharu.core.filter;

import com.osamuharu.core.utils.NetworkUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Slf4j
public class ApiLoggingFilter extends OncePerRequestFilter {

  private static final String REQUEST_LOG = "[REQ]  {} {} | ip={}";
  private static final String RESPONSE_LOG = "[RES]  {} {} | ip={} | status={} | duration={}ms";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      @NonNull HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException {

    long startTime = System.currentTimeMillis();
    String method = request.getMethod();
    String uri = request.getRequestURI();
    String clientIp = NetworkUtils.getClientIp(request);

    log.info(REQUEST_LOG, method, uri, clientIp);

    try {
      filterChain.doFilter(request, response);
    } finally {
      log.info(RESPONSE_LOG,
          method, uri, clientIp,
          response.getStatus(),
          System.currentTimeMillis() - startTime);
    }
  }
}