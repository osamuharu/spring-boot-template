package com.osamuharu.security.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCredentialsDto {

  private String username;
  private String password;
}
