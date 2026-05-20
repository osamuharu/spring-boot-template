package com.osamuharu.shared.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestResponseDto<T> {

  private int statusCode;
  private String message;
  private T data;
}
