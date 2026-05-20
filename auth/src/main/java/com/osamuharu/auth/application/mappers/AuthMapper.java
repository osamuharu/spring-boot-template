package com.osamuharu.auth.application.mappers;

import com.osamuharu.auth.presentation.dto.requests.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.responses.LoginResponseDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.user.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

  User toDomain(RegisterRequestDto registerRequestDto);

  LoginResponseDto toDto(User user, TokenDto acccessTokenDto, String type);
}
