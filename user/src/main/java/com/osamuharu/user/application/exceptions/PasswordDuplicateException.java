package com.osamuharu.user.application.exceptions;

import com.osamuharu.shared.exceptions.AppException;
import org.springframework.http.HttpStatus;

public class PasswordDuplicateException extends AppException {

  public PasswordDuplicateException() {
    super("PasswordDuplicate", HttpStatus.BAD_REQUEST,
        "New password cannot be the same as the old password", null);
  }
}
