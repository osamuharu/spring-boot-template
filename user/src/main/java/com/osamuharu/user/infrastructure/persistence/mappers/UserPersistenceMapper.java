package com.osamuharu.user.infrastructure.persistence.mappers;


import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

  User toDomain(UserEntity userEntity);

  UserEntity toEntity(User user);
}

