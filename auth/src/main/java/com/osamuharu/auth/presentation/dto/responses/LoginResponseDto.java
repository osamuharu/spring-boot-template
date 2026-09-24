package com.osamuharu.auth.presentation.dto.responses;

import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.shared.dtos.UserDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

  UserDto user;
  TokenDto accessTokenDto;
  String type = "Bearer";
}
