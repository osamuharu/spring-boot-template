package com.osamuharu.user.infrastructure.persistence.mappers;


import com.osamuharu.file.infrastructure.persistence.entities.FileEntity;
import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.infrastructure.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserPersistenceMapper {

  @Mapping(source = "avatar.id", target = "avatarId")
  User toDomain(UserEntity userEntity);

  @Mapping(source = "avatarId", target = "avatar")
  UserEntity toEntity(User user);

  default FileEntity mapAvatarIdToFileEntity(String avatarId) {
    if (avatarId == null || avatarId.trim().isEmpty()) {
      return null;
    }

    FileEntity file = new FileEntity();
    file.setId(avatarId);
    return file;
  }
}

