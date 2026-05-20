package com.osamuharu.shared.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSecurityDto {

  private String username;
  private String password;
}
