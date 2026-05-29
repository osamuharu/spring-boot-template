package com.osamuharu.auth.application.useCases;

import com.osamuharu.security.ports.PasswordPort;
import com.osamuharu.user.application.exceptions.EmailExistsException;
import com.osamuharu.user.application.exceptions.UserCannotNullException;
import com.osamuharu.user.application.exceptions.UserNameExistsException;
import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterUseCase {

  private final UserRepository userRepository;
  private final PasswordPort passwordPort;

  public void execute(User user) {
    if (user == null) {
      throw new UserCannotNullException();
    }

    if (userRepository.existsByEmail(user.getEmail())) {
      throw new EmailExistsException();
    }

    if (userRepository.existsByUsername(user.getUsername())) {
      throw new UserNameExistsException();
    }

    String hashedPassword = passwordPort.hashPassword(user.getPassword());
    user.changePassword(hashedPassword);

    userRepository.save(user);
  }
}
