package com.osamuharu.user.application.exceptions;

import com.osamuharu.shared.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class InvalidUserException extends AppException {

  public InvalidUserException() {
    super("InvalidUser", HttpStatus.BAD_REQUEST, "Invalid user", null);
  }
}
