package com.osamuharu.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class AppException extends RuntimeException {
    private final String name;
    private final HttpStatus statusCode;
    private final Object error;

    public AppException(String name, HttpStatus statusCode, String message, Object error) {
        super(message);
        this.statusCode = statusCode;
        this.name = name;
        this.error = error;
    }
}
