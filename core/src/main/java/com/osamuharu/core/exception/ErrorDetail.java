package com.osamuharu.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
    private String name;
    private int status;
    private String message;
    private long timestamp;
    private Object error;
}
