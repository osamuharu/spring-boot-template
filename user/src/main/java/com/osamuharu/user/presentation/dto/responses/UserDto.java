package com.osamuharu.user.presentation.dto.responses;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  @JsonProperty(index = 1)
  private Long id;

  private String firstName;
  private String lastName;
  private String username;
  private String email;

  private Instant createdAt;
  private Instant updatedAt;
}
