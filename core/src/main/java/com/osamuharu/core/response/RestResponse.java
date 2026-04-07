package com.osamuharu.core.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestResponse<T> {
    private Integer statusCode;
    private Object message;
    private T data;

}
