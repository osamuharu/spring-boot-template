package com.osamuharu.auth.application.exceptions;

import com.osamuharu.shared.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends AppException {

  public InvalidPasswordException() {
    super("InvalidPassword", HttpStatus.BAD_REQUEST, "Invalid password", null);
  }
}
