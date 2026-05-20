package com.osamuharu.shared.exceptions;

import com.osamuharu.shared.dtos.ErrorResponseDto;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleUnwantedException(Exception ex) {
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .name("ServerError")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Something went wrong")
        .timestamp(System.currentTimeMillis())
        .error("System error: " + ex.getMessage())
        .build();
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(errorResponse);
  }

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ErrorResponseDto> handleAppException(AppException ex) {
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
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
  public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {

    Map<String, String> errors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
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
  public ResponseEntity<ErrorResponseDto> handleNoHandlerFoundException(
      NoHandlerFoundException ex) {

    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
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


}
