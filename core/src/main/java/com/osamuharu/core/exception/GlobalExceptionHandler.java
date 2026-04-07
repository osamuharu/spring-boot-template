package com.osamuharu.core.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice(basePackages = "com.osamuharu")
public class GlobalExceptionHandler {

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ErrorResponse> handleAppException(AppException ex) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .name(ex.getName())
        .status(ex.getStatusCode()
            .value())
        .message(ex.getMessage())
        .timestamp(System.currentTimeMillis())
        .error(ex.getError())
        .build();

    return ResponseEntity
        .status(ex.getStatusCode())
        .body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    ErrorResponse errorResponse = ErrorResponse.builder()
        .name("MethodArgumentNotValidException")
        .status(ex.getStatusCode()
            .value())
        .message("Field validation error")
        .timestamp(System.currentTimeMillis())
        .error(errors)
        .build();

    return ResponseEntity
        .status(ex.getStatusCode()
            .value())
        .body(errorResponse);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex) {

    ErrorResponse errorResponse = ErrorResponse.builder()
        .name("NoHandlerFoundException")
        .status(ex.getStatusCode()
            .value())
        .message("API path not found")
        .timestamp(System.currentTimeMillis())
        .error(null)
        .build();

    return ResponseEntity
        .status(ex.getStatusCode()
            .value())
        .body(errorResponse);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnwantedException(Exception ex) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .name("ServerError")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Something went wrong")
        .timestamp(System.currentTimeMillis())
        .error("System error: " + ex.getMessage() + ex.getClass()
            .getSimpleName())
        .build();

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(errorResponse);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleInsufficientAuthenticationException(
      InsufficientAuthenticationException ex) {
    ErrorResponse errorResponse = ErrorResponse.builder()
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

  @ExceptionHandler(JwtException.class)
  public ResponseEntity<ErrorResponse> handleJwtException(JwtException ex) {
    ErrorResponse errorResponse = ErrorResponse.builder()
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
