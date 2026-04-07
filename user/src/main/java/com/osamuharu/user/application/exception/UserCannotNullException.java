package com.osamuharu.user.application.exception;

import com.osamuharu.shared.exception.AppException;
import org.springframework.http.HttpStatus;

public class UserCannotNullException extends AppException {

  public UserCannotNullException() {
    super("UserCannotNull", HttpStatus.BAD_REQUEST, "User cannot be null", null);
  }
}
