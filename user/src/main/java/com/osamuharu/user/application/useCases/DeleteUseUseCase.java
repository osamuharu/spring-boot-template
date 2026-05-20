package com.osamuharu.user.application.useCases;

import com.osamuharu.user.domain.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteUseUseCase {

  private final UserRepository repository;

  public void execute(Long id) {
    if (id <= 0) {
      throw new IllegalArgumentException("Invalid user id: " + id);
    }

    if (!repository.existsById(id)) {
      throw new RuntimeException("User not found with id: " + id);
    }

    repository.deleteById(id);
  }
}
