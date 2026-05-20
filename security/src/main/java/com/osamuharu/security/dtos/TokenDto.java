package com.osamuharu.security.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenDto {

  private String token;
  private long expiresIn;
}
