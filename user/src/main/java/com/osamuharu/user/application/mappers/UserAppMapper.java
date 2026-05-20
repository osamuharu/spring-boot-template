package com.osamuharu.user.application.mappers;


import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.presentation.dto.requests.CreateUserDto;
import com.osamuharu.user.presentation.dto.requests.UpdateUserDto;
import com.osamuharu.user.presentation.dto.responses.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAppMapper {

  User toDomain(CreateUserDto userDto);

  User toDomain(UpdateUserDto updateUserDto);

  UserDto toDto(User user);

}

