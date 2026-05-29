package com.osamuharu.user.application.useCases;

import com.osamuharu.security.ports.PasswordPort;
import com.osamuharu.user.application.exceptions.EmailExistsException;
import com.osamuharu.user.application.exceptions.UserCannotNullException;
import com.osamuharu.user.application.exceptions.UserNameExistsException;
import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.domain.repositories.UserRepository;
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
    user.changePassword(hashedPassword);

    return repository.save(user);
  }
}
