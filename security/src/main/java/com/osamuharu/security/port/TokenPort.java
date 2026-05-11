package com.osamuharu.security.port;


import com.osamuharu.security.dto.PayloadDto;
import com.osamuharu.security.dto.TokenDto;
import java.time.Instant;

public interface TokenPort {

  TokenDto generateAccessToken(PayloadDto payloadDto);

  PayloadDto extractPayload(String token);

  String extractIdToken(String token);

  Instant extractExpiration(String token);

  boolean isTokenInvalid(String token);
}
