package com.osamuharu.security.ports;


import com.osamuharu.security.dtos.PayloadDto;
import com.osamuharu.security.dtos.TokenDto;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import org.springframework.security.core.Authentication;

public interface TokenPort {

  TokenDto generateAccessToken(Authentication authentication)
      throws UserPrincipalNotFoundException;

  PayloadDto extractPayload(String token);

  String extractIdToken(String token);

  Instant extractExpiration(String token);

  boolean isValidToken(String token);
}
