package com.osamuharu.core.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osamuharu.core.exception.ErrorResponse;
import com.osamuharu.shared.annotation.ResponseMessage;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@RestControllerAdvice(basePackages = "com.osamuharu")
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public boolean supports(
      @NonNull MethodParameter returnType,
      @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public @Nullable Object beforeBodyWrite(@Nullable Object body,
      @NonNull MethodParameter returnType,
      @NonNull MediaType selectedContentType,
      @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
      @NonNull ServerHttpRequest request,
      @NonNull ServerHttpResponse response) {

    if (body instanceof ErrorResponse) {
      return body;
    }

    if (!(response instanceof ServletServerHttpResponse)) {
      return body;
    }

    int status = ((ServletServerHttpResponse) response).getServletResponse()
        .getStatus();

    ResponseMessage responseMessage = returnType.getMethodAnnotation(ResponseMessage.class);
    String message = "";

    if (responseMessage != null) {
      message = responseMessage.value();
    }

    RestResponse<Object> restResponse = RestResponse
        .builder()
        .data(body)
        .statusCode(status)
        .message(message)
        .build();

    if (body instanceof String || selectedConverterType.isAssignableFrom(
        StringHttpMessageConverter.class)) {
      try {
        return objectMapper.writeValueAsString(restResponse);
      } catch (JsonProcessingException e) {
        throw new RuntimeException("Failed to convert RestResponse to JSON string", e);
      }
    }

    return restResponse;
  }
}
