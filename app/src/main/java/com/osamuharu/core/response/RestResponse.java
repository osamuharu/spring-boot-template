package com.osamuharu.core.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestResponse<T> {
	private int statusCode;
	private String message;
	private T data;
}
