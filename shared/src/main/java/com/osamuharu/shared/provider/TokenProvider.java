package com.osamuharu.shared.provider;

import com.osamuharu.shared.entity.Payload;
import com.osamuharu.shared.entity.Token;

public interface TokenProvider {

  Token generateAccessToken(Payload payload);

  Payload extractPayload(String token);

  boolean isTokenInvalid(String token);
}
