package com.osamuharu.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorDetail> handleAppException(AppException ex) {
        return ResponseEntity
                       .status(ex.getStatusCode())
                       .body(new ErrorDetail(
                               ex.getName(),
                               ex.getStatusCode().value(),
                               ex.getMessage(),
                               System.currentTimeMillis(), ex.getError()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity
                       .status(ex.getStatusCode().value())
                       .body(new ErrorDetail(
                               "MethodArgumentNotValidException",
                               ex.getStatusCode().value(),
                               "Field validation error",
                               System.currentTimeMillis(), errors));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetail> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity
                       .status(ex.getStatusCode().value())
                       .body(new ErrorDetail(
                               "NoHandlerFoundException",
                               ex.getStatusCode().value(),
                               "API path not found",
                               System.currentTimeMillis(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleUnwantedException(Exception ex) {
        return ResponseEntity
                       .status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(new ErrorDetail("ServerError", 500, "Something went wrong",
                               System.currentTimeMillis(), "System error: " + ex.getMessage() + ex.getClass().getSimpleName()));
    }
}
