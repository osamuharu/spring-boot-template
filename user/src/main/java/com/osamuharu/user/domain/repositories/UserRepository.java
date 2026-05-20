package com.osamuharu.user.domain.repositories;

import com.osamuharu.user.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  User save(User user);

  void deleteById(Long id);

  List<User> findAll();

  Optional<User> findById(Long id);

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  boolean existsById(Long id);
}
