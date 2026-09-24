package com.osamuharu.auth.application.useCases;

import com.osamuharu.auth.presentation.dto.requests.LoginRequestDto;
import com.osamuharu.security.ports.InternalSecurityPort;
import com.osamuharu.security.ports.UserCredentialsPort;
import com.osamuharu.shared.dtos.UserDto;
import com.osamuharu.user.application.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginUseCase {

  private final InternalSecurityPort internalSecurityPort;
  private final UserCredentialsPort userCredentialsPort;

  public UserDto execute(LoginRequestDto dto) {
    UserDto userDto = userCredentialsPort.loadUserByEmail(dto.getEmail())
        .orElseThrow(UserNotFoundException::new);

    internalSecurityPort.setContextAsUser(userDto.getUsername());

    return userDto;
  }
}
