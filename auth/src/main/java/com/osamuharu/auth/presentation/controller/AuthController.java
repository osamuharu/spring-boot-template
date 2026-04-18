package com.osamuharu.auth.presentation.controller;

import com.osamuharu.auth.application.service.AuthService;
import com.osamuharu.auth.presentation.dto.request.LoginRequestDto;
import com.osamuharu.auth.presentation.dto.request.RegisterRequestDto;
import com.osamuharu.auth.presentation.dto.response.LoginResponseDto;
import com.osamuharu.shared.annotation.ResponseMessage;
import com.osamuharu.shared.utils.TokenUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/{version}/auth", version = "v1")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseMessage("Register successfully")
  public boolean register(@Valid @RequestBody RegisterRequestDto dto) {
    authService.register(dto);
    return true;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  @ResponseMessage("Login successfully")
  public LoginResponseDto login(@Valid @RequestBody LoginRequestDto dto) {
    return authService.login(dto);
  }

  @DeleteMapping("/logout")
  @ResponseMessage("Logout successfully")
  @ResponseStatus(HttpStatus.OK)
  public boolean logout(@RequestHeader(value = "Authorization", required = false) String authHeader)
      throws IllegalAccessException {
    String token = TokenUtils.extractTokenFromHeader(authHeader);

    if (token == null) {
      throw new IllegalAccessException("Authorization header is missing or invalid");
    }

    authService.logout(token);
    return true;
  }
}
