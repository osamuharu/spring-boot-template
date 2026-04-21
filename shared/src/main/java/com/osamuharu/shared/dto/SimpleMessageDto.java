package com.osamuharu.shared.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleMessageDto {

  @Email(message = "Invalid email format for recipient")
  @NotBlank(message = "Recipient email must not be blank")
  private String to;

  @NotBlank(message = "Email subject must not be blank")
  private String subject;

  @NotBlank(message = "Email body must not be blank")
  private String text;
}
