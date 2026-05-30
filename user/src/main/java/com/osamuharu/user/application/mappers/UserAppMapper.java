package com.osamuharu.user.application.mappers;


import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.presentation.dto.requests.CreateUserDto;
import com.osamuharu.user.presentation.dto.requests.UpdateUserDto;
import com.osamuharu.user.presentation.dto.responses.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAppMapper {

  User toDomain(CreateUserDto userDto);

  User toDomain(UpdateUserDto updateUserDto);

  @Mapping(source = "timestamp.createdAt", target = "createdAt")
  @Mapping(source = "timestamp.updatedAt", target = "updatedAt")
  UserDto toDto(User user);
}

