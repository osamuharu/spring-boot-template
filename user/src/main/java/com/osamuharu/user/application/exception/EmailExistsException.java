package com.osamuharu.user.application.exception;

import com.osamuharu.shared.exception.AppException;
import org.springframework.http.HttpStatus;

public class EmailExistsException extends AppException {

  public EmailExistsException(String email) {
    super("EmailExists", HttpStatus.BAD_REQUEST, "Email already exists: " + email, null);
  }
}
