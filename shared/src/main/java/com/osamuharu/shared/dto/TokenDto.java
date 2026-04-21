package com.osamuharu.shared.dto;

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
