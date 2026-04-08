package com.osamuharu.auth.presentation.dto.response;

import com.osamuharu.shared.entity.Token;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

  String username;
  Token accessToken;
  String type = "Bearer";
}
