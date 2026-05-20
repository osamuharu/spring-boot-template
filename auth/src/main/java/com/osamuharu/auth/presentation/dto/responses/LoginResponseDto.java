package com.osamuharu.auth.presentation.dto.responses;

import com.osamuharu.security.dtos.TokenDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

  String username;
  TokenDto accessTokenDto;
  String type = "Bearer";
}
