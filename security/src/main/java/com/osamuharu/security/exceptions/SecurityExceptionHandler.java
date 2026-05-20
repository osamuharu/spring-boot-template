package com.osamuharu.security.exceptions;

import com.osamuharu.shared.dtos.ErrorResponseDto;
import io.jsonwebtoken.JwtException;
import javax.naming.AuthenticationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) // prioritize this handler for security exceptions
public class SecurityExceptionHandler {

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponseDto> handleInsufficientAuthenticationException(
      AuthenticationException ex) {
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .name("AuthenticationException")
        .status(HttpStatus.UNAUTHORIZED
            .value())
        .message(ex.getMessage())
        .timestamp(System.currentTimeMillis())
        .error(null)
        .build();

    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED
            .value())
        .body(errorResponse);
  }

  @ExceptionHandler(AuthorizationDeniedException.class)
  public ResponseEntity<ErrorResponseDto> handleAccessDeniedException(
      AuthorizationDeniedException ex) {
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .name("AuthorizationDeniedException")
        .status(HttpStatus.FORBIDDEN
            .value())
        .message(ex.getMessage())
        .timestamp(System.currentTimeMillis())
        .error(null)
        .build();

    return ResponseEntity
        .status(HttpStatus.FORBIDDEN
            .value())
        .body(errorResponse);
  }

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<ErrorResponseDto> handleJwtException(JwtException ex) {
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .name("JwtException")
        .status(HttpStatus.UNAUTHORIZED
            .value())
        .message(ex.getMessage())
        .timestamp(System.currentTimeMillis())
        .error(null)
        .build();

    return ResponseEntity
        .status(HttpStatus.UNAUTHORIZED
            .value())
        .body(errorResponse);
  }
}
