package com.osamuharu.auth.presentation.dto.responses;

import com.osamuharu.security.dtos.TokenDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

  TokenDto accessToken;
  String type = "Bearer";
}
