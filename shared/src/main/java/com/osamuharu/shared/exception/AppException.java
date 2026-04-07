package com.osamuharu.shared.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AppException extends RuntimeException {

  private String name;
  private HttpStatus statusCode;
  private Object error;

  public AppException(String name, HttpStatus statusCode, String message, Object error) {
    super(message);
    this.statusCode = statusCode;
    this.name = name;
    this.error = error;
  }
}
