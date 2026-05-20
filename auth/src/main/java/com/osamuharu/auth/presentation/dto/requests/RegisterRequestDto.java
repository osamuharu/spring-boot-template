package com.osamuharu.auth.presentation.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {

  @NotBlank(message = "First name must not be blank")
  @Size(max = 50, message = "First name must not exceed 50 characters")
  private String firstName;

  @NotBlank(message = "Last name must not be blank")
  @Size(max = 50, message = "Last name must not exceed 50 characters")
  private String lastName;

  @NotBlank(message = "Username must not be blank")
  @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
  @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message =
      "Username can only contain letters, numbers, dots, hyphens, and" +
          " " +
          "underscores")
  private String username;

  @NotBlank(message = "Password must not be blank")
  @Size(min = 8, max = 255, message = "Password must be at least 8 characters long")
  private String password;

  @NotBlank(message = "Email must not be blank")
  @Email(message = "Invalid email format")
  @Size(max = 100, message = "Email must not exceed 100 characters")
  private String email;
}
