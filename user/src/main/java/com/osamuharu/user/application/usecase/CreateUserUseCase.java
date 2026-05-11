package com.osamuharu.user.application.usecase;

import com.osamuharu.security.port.PasswordPort;
import com.osamuharu.user.application.exception.EmailExistsException;
import com.osamuharu.user.application.exception.UserCannotNullException;
import com.osamuharu.user.application.exception.UserNameExistsException;
import com.osamuharu.user.domain.entity.User;
import com.osamuharu.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCase {

  private final UserRepository repository;
  private final PasswordPort passwordPort;

  public User execute(User user) {
    if (user == null) {
      throw new UserCannotNullException();
    }

    if (repository.existsByEmail(user.getEmail())) {
      throw new EmailExistsException(user.getEmail());
    }

    if (repository.existsByUsername(user.getUsername())) {
      throw new UserNameExistsException(user.getUsername());
    }

    String hashedPassword = passwordPort.hashPassword(user.getPassword());
    user.setPassword(hashedPassword);

    return repository.save(user);
  }
}
