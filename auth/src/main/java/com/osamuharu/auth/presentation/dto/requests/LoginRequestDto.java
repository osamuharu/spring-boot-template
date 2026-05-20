package com.osamuharu.auth.presentation.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Invalid email format")
  @Size(max = 100, message = "Email must not exceed 100 characters")
  private String email;

  @NotBlank(message = "Password must not be blank")
  @Size(max = 255, message = "Password must not exceed 255 characters")
  private String password;
}
