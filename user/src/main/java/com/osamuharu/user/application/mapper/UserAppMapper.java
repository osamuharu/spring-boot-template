package com.osamuharu.user.application.mapper;


import com.osamuharu.user.domain.entity.User;
import com.osamuharu.user.presentation.dto.request.CreateUserDto;
import com.osamuharu.user.presentation.dto.request.UpdateUserDto;
import com.osamuharu.user.presentation.dto.response.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAppMapper {

  User toDomain(CreateUserDto userDto);

  User toDomain(UpdateUserDto updateUserDto);

  UserDto toDto(User user);

}

