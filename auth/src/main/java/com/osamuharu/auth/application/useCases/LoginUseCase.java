package com.osamuharu.auth.application.useCases;

import com.osamuharu.auth.presentation.dto.requests.LoginRequestDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.security.ports.InternalSecurityPort;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.security.ports.UserCredentialsPort;
import com.osamuharu.shared.dtos.UserDto;
import com.osamuharu.user.application.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
public class LoginUseCase {

  private final UserCredentialsPort userCredentialsPort;
  private final TokenPort tokenPort;
  private final UserDetailsService userDetailsService;
  private final InternalSecurityPort internalSecurityPort;
  private final AuthenticationManager authenticationManager;

  public TokenDto execute(LoginRequestDto dto) {

    UserDto userDto = userCredentialsPort.loadUserByEmail(dto.getEmail())
        .orElseThrow(UserNotFoundException::new);

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userDto.getUsername(), dto.getPassword())
    );

    UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());

    TokenDto accessTokenDto = tokenPort.generateAccessToken(userDetails);

    internalSecurityPort.setContextAsUser(userDetails, accessTokenDto.getToken());

    return accessTokenDto;
  }
}
