package com.osamuharu.user.application.usecase;

import com.osamuharu.shared.port.PasswordPost;
import com.osamuharu.user.domain.entity.User;
import com.osamuharu.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUseCase {

  private final UserRepository repository;
  private final PasswordPost passwordPost;

  public User execute(Long id, User user) {
    if (user == null) {
      return null;
    }

    User existsUser = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    if (user.getEmail() != null) {
      if (repository.existsByEmail(user.getEmail())) {
        throw new IllegalArgumentException("Email already exists: " + user.getEmail());
      }

      existsUser.changeEmail(user.getEmail());
    }

    if (user.getUsername() != null) {
      if (repository.existsByUsername(user.getUsername())) {
        throw new IllegalArgumentException("Username already exists: " + user.getUsername());
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
        throw new IllegalArgumentException("New password cannot be the same as the old password");
      }

      existsUser.changePassword(user.getPassword());

      String hashedPassword = passwordPost.hashPassword(existsUser.getPassword());
      existsUser.setPassword(hashedPassword);
    }

    return repository.save(existsUser);
  }
}
