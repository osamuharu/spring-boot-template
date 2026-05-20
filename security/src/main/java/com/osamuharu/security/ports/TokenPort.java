package com.osamuharu.security.ports;


import com.osamuharu.security.dtos.PayloadDto;
import com.osamuharu.security.dtos.TokenDto;
import java.time.Instant;

public interface TokenPort {

  TokenDto generateAccessToken(PayloadDto payloadDto);

  PayloadDto extractPayload(String token);

  String extractIdToken(String token);

  Instant extractExpiration(String token);

  boolean isTokenInvalid(String token);
}
