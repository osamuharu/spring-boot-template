package com.osamuharu.auth.presentation.dto.response;

import com.osamuharu.security.dto.TokenDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

  String username;
  TokenDto accessTokenDto;
  String type = "Bearer";
}
