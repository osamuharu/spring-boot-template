package com.osamuharu.user.application.useCases;

import com.osamuharu.user.application.exceptions.InvalidUserException;
import com.osamuharu.user.application.exceptions.UserNotFoundException;
import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUseUseCase {

  private final UserRepository repository;

  public void execute(Long id) {
    if (id <= 0) {
      throw new InvalidUserException();
    }

    if (!repository.existsById(id)) {
      throw new UserNotFoundException();
    }

    repository.deleteById(id);
  }
}
