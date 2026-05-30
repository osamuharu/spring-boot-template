package com.osamuharu.user.presentation.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDto {

  @Size(max = 50, message = "First name must not exceed 50 characters")
  private String firstName;

  @Size(max = 50, message = "Last name must not exceed 50 characters")
  private String lastName;

  @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
  @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message =
      "Username can only contain letters, numbers, dots, hyphens, and" +
          " " + "underscores")
  private String username;

  @Size(min = 8, max = 255, message = "Password must be at least 8 characters long")
  private String password;

  @Email(message = "Invalid email format")
  @Size(max = 100, message = "Email must not exceed 100 characters")
  private String email;

  private String avatarId;
}
