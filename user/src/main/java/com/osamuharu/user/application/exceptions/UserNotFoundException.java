package com.osamuharu.user.application.exceptions;

import com.osamuharu.shared.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AppException {

  public UserNotFoundException() {
    super("UserNotFound", HttpStatus.BAD_REQUEST, "User not found", null);
  }
}
