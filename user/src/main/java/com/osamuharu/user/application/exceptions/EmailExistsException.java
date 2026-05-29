package com.osamuharu.user.application.exceptions;

import com.osamuharu.shared.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class EmailExistsException extends AppException {

  public EmailExistsException() {
    super("EmailExists", HttpStatus.BAD_REQUEST, "Email already exists", null);
  }
}
