package com.osamuharu.user.infrastructure.persistence.adapters;

import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.domain.repositories.UserRepository;
import com.osamuharu.user.infrastructure.persistence.entities.UserEntity;
import com.osamuharu.user.infrastructure.persistence.mappers.UserPersistenceMapper;
import com.osamuharu.user.infrastructure.persistence.repositories.UserJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRepositoryAdapter implements UserRepository {

  private final UserJpaRepository jpaRepository;
  private final UserPersistenceMapper mapper;

  @Override
  public User save(User user) {
    UserEntity entity = mapper.toEntity(user);
    UserEntity savedEntity = jpaRepository.save(entity);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }

  @Override
  public List<User> findAll() {
    return jpaRepository.findAll()
        .stream()
        .map(mapper::toDomain)
        .toList();
  }

  @Override
  public Optional<User> findById(Long id) {
    return jpaRepository.findById(id)
        .map(mapper::toDomain);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return jpaRepository.findByUsername(username)
        .map(mapper::toDomain);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return jpaRepository.findByEmail(email)
        .map(mapper::toDomain);
  }

  @Override
  public boolean existsByUsername(String username) {
    return jpaRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return jpaRepository.existsByEmail(email);
  }

  @Override
  public boolean existsById(Long id) {
    return jpaRepository.existsById(id);
  }
}
