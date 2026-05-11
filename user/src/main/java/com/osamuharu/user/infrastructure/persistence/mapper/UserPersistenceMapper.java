package com.osamuharu.user.infrastructure.persistence.mapper;


import com.osamuharu.user.domain.entity.User;
import com.osamuharu.user.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

  User toDomain(UserEntity userEntity);

  UserEntity toEntity(User user);
}

