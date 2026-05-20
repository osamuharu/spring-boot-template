package com.osamuharu.user.application.mappers.impl;

import com.osamuharu.user.application.mappers.UserAppMapper;
import com.osamuharu.user.domain.entities.User;
import com.osamuharu.user.presentation.dto.requests.CreateUserDto;
import com.osamuharu.user.presentation.dto.requests.UpdateUserDto;
import com.osamuharu.user.presentation.dto.responses.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserAppMapperImpl implements UserAppMapper {

  @Override
  public User toDomain(CreateUserDto userDto) {
    if (userDto == null) {
      return null;
    }

    return User.builder()
        .username(userDto.getUsername())
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .email(userDto.getEmail())
        .password(userDto.getPassword())
        .build();
  }

  @Override
  public User toDomain(UpdateUserDto updateUserDto) {
    if (updateUserDto == null) {
      return null;
    }

    return User.builder()
        .username(updateUserDto.getUsername())
        .firstName(updateUserDto.getFirstName())
        .lastName(updateUserDto.getLastName())
        .email(updateUserDto.getEmail())
        .password(updateUserDto.getPassword())
        .build();
  }

  @Override
  public UserDto toDto(User user) {
    if (user == null) {
      return null;
    }

    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setUsername(user.getUsername());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setEmail(user.getEmail());

    return userDto;
  }
}
