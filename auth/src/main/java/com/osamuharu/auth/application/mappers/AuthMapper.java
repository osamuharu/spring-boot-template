package com.osamuharu.auth.application.mappers;

import com.osamuharu.auth.presentation.dto.requests.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.responses.LoginResponseDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.user.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
  
  User toDomain(RegisterRequestDto registerRequestDto);

  @Mapping(source = "user.username", target = "username")
  @Mapping(source = "acccessTokenDto", target = "accessTokenDto")
  LoginResponseDto toDto(User user, TokenDto acccessTokenDto, String type);
}
