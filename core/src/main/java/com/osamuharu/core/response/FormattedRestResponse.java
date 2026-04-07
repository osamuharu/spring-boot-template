package com.osamuharu.core.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice
public class FormattedRestResponse implements ResponseBodyAdvice<Object> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean supports(
            @NonNull MethodParameter returnType,
            @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        String packageName = returnType.getContainingClass().getPackageName();

        return !packageName.contains("org.springdoc") &&
                       !packageName.contains("swagger") &&
                       !packageName.contains("org.springframework.boot.autoconfigure.web.servlet.error");


    }

    @Override
    public @Nullable Object beforeBodyWrite(@Nullable Object body,
                                            @NonNull MethodParameter returnType,
                                            @NonNull MediaType selectedContentType,
                                            @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                            @NonNull ServerHttpRequest request,
                                            @NonNull ServerHttpResponse response) {

        if (body instanceof Resource || body instanceof RestResponse) {
            return body;
        }
        int status = 200;
        if (response instanceof ServletServerHttpResponse servletResponse) {
            HttpServletResponse httpServletResponse = servletResponse.getServletResponse();
            status = httpServletResponse.getStatus();
        }


        RestResponse<Object> wrappedResponse = RestResponse
                                                       .builder()
                                                       .data(body)
                                                       .statusCode(status)
                                                       .message(this.getMessage(status))
                                                       .build();

        if (body instanceof String || selectedConverterType.isAssignableFrom(StringHttpMessageConverter.class)) {
            try {
                return objectMapper.writeValueAsString(wrappedResponse);
            } catch (JsonProcessingException e) {
                return "{\"message\": \"Error processing string response\"}";
            }
        }

        return wrappedResponse;
    }

    private String getMessage(int status) {
        return status >= 400 ? "CALL API FAILED" : "CALL API SUCCESS";
    }
}
