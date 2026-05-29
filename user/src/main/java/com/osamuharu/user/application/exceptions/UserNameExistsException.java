package com.osamuharu.user.application.exceptions;

import com.osamuharu.shared.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class UserNameExistsException extends AppException {

  public UserNameExistsException() {
    super("UserNameExists", HttpStatus.BAD_REQUEST, "Username already exists", null);
  }
}
