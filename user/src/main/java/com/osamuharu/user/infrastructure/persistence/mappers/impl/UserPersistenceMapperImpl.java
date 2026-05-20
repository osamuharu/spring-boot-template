package com.osamuharu.user.infrastructure.persistence.mappers.impl;

import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.infrastructure.persistence.entities.UserEntity;
import com.osamuharu.user.infrastructure.persistence.mappers.UserPersistenceMapper;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapperImpl implements UserPersistenceMapper {

  @Override
  public User toDomain(UserEntity userEntity) {
    if (userEntity == null) {
      return null;
    }

    return User.builder()
        .id(userEntity.getId())
        .username(userEntity.getUsername())
        .firstName(userEntity.getFirstName())
        .lastName(userEntity.getLastName())
        .email(userEntity.getEmail())
        .password(userEntity.getPassword())
        .build();
  }

  @Override
  public UserEntity toEntity(User user) {
    if (user == null) {
      return null;
    }

    UserEntity userEntity = new UserEntity();
    userEntity.setId(user.getId());
    userEntity.setUsername(user.getUsername());
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());
    userEntity.setEmail(user.getEmail());
    userEntity.setPassword(user.getPassword());

    return userEntity;
  }
}
