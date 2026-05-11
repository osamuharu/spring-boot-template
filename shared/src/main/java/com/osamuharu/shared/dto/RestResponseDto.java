package com.osamuharu.shared.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestResponseDto<T> {

  private int statusCode;
  private String message;
  private T data;
}
