package com.osamuharu.shared.provider;

import com.osamuharu.shared.entity.Payload;
import com.osamuharu.shared.entity.Token;
import java.time.Instant;

public interface TokenProvider {

  Token generateAccessToken(Payload payload);

  Payload extractPayload(String token);

  String extractIdToken(String token);

  Instant extractExpiration(String token);

  boolean isTokenInvalid(String token);
}
