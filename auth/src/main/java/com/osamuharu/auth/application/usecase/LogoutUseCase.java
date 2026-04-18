package com.osamuharu.auth.application.usecase;

import com.osamuharu.shared.provider.MemoryProvider;
import com.osamuharu.shared.provider.TokenProvider;
import java.time.Duration;
import java.time.Instant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutUseCase {

  private final TokenProvider tokenProvider;
  private final MemoryProvider memoryProvider;

  public void execute(String token) throws IllegalAccessException {
    String idToken = tokenProvider.extractIdToken(token);

    if (idToken == null) {
      throw new IllegalAccessException("Invalid token");
    }

    Instant now = Instant.now();
    Instant expiration = tokenProvider.extractExpiration(token);

    long expiresIn = Duration.between(now, expiration).getSeconds();

    if (expiresIn <= 0) {
      return;
    }

    memoryProvider.saveTokenInBlacklist(idToken, expiresIn);
  }
}
