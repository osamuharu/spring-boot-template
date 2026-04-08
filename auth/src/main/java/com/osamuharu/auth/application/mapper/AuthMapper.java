package com.osamuharu.auth.application.mapper;

import com.osamuharu.auth.presentation.dto.request.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.response.LoginResponseDto;
import com.osamuharu.shared.entity.Token;
import com.osamuharu.user.domain.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

  User toDomain(RegisterRequestDto registerRequestDto);

  LoginResponseDto toDto(User user, Token acccessToken, String type);
}
