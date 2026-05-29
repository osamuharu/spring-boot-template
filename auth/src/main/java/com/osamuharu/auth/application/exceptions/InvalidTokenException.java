package com.osamuharu.auth.application.exceptions;

import com.osamuharu.shared.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends AppException {

  public InvalidTokenException() {
    super("InvalidToken", HttpStatus.BAD_REQUEST, "Invalid token", null);
  }
}
