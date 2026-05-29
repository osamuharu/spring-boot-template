package com.osamuharu.user.application.useCases;

import com.osamuharu.security.ports.PasswordPort;
import com.osamuharu.user.application.exceptions.EmailExistsException;
import com.osamuharu.user.application.exceptions.PasswordDuplicateException;
import com.osamuharu.user.application.exceptions.UserCannotNullException;
import com.osamuharu.user.application.exceptions.UserNameExistsException;
import com.osamuharu.user.application.exceptions.UserNotFoundException;
import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUseCase {

  private final UserRepository repository;
  private final PasswordPort passwordPost;

  public User execute(Long id, User user) {
    if (user == null) {
      throw new UserCannotNullException();
    }

    User existsUser = repository.findById(id)
        .orElseThrow(UserNotFoundException::new);

    if (user.getEmail() != null) {
      if (repository.existsByEmail(user.getEmail())) {
        throw new EmailExistsException();
      }

      existsUser.changeEmail(user.getEmail());
    }

    if (user.getUsername() != null) {
      if (repository.existsByUsername(user.getUsername())) {
        throw new UserNameExistsException();
      }
      existsUser.changeUsername(user.getUsername());
    }

    if (user.getFirstName() != null) {
      existsUser.changeFirstName(user.getFirstName());
    }

    if (user.getLastName() != null) {
      existsUser.changeLastName(user.getLastName());
    }

    if (user.getPassword() != null) {

      if (passwordPost.verifyPassword(user.getPassword(), existsUser.getPassword())) {
        throw new PasswordDuplicateException();
      }

      String hashedPassword = passwordPost.hashPassword(user.getPassword());
      existsUser.changePassword(hashedPassword);
    }

    return repository.save(existsUser);
  }
}
