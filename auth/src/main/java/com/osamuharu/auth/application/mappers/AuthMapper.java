package com.osamuharu.auth.application.mappers;

import com.osamuharu.auth.presentation.dto.requests.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.responses.LoginResponseDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.user.presentation.dto.requests.CreateUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {

  @Mapping(source = "accessTokenDto", target = "accessToken")
  LoginResponseDto toDto(TokenDto accessTokenDto, String type);

  CreateUserDto toDto(RegisterRequestDto registerRequestDto);
}
