package com.osamuharu.auth.application.mappers.impl;

import com.osamuharu.auth.application.mappers.AuthMapper;
import com.osamuharu.auth.presentation.dto.requests.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.responses.LoginResponseDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.user.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImpl implements AuthMapper {

  @Override
  public User toDomain(RegisterRequestDto registerRequestDto) {
    if (registerRequestDto == null) {
      return null;
    }

    return User.builder()
        .firstName(registerRequestDto.getFirstName())
        .lastName(registerRequestDto.getLastName())
        .username(registerRequestDto.getUsername())
        .email(registerRequestDto.getEmail())
        .password(registerRequestDto.getPassword())
        .build();
  }

  @Override
  public LoginResponseDto toDto(User user, TokenDto acccessTokenDto, String type) {
    if (user == null) {
      return null;
    }

    LoginResponseDto responseDto = new LoginResponseDto();
    responseDto.setUsername(user.getUsername());
    responseDto.setAccessTokenDto(acccessTokenDto);
    responseDto.setType(type);

    return responseDto;
  }
}
