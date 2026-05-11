package com.osamuharu.shared.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDto {

  private String name;
  private int status;
  private String message;
  private long timestamp;
  private Object error;
}
