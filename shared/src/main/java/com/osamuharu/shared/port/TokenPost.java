package com.osamuharu.shared.port;

import com.osamuharu.shared.dto.PayloadDto;
import com.osamuharu.shared.dto.TokenDto;
import java.time.Instant;

public interface TokenPost {

  TokenDto generateAccessToken(PayloadDto payloadDto);

  PayloadDto extractPayload(String token);

  String extractIdToken(String token);

  Instant extractExpiration(String token);

  boolean isTokenInvalid(String token);
}
