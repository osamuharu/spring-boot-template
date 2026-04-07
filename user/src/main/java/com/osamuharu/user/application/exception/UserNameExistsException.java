package com.osamuharu.user.application.exception;

import com.osamuharu.shared.exception.AppException;
import org.springframework.http.HttpStatus;

public class UserNameExistsException extends AppException {

  public UserNameExistsException(String username) {
    super("UserNameExists", HttpStatus.BAD_REQUEST, "Username already exists: " + username, null);
  }
}
