package com.osamuharu.auth.application.usecase;

import com.osamuharu.shared.port.MemoryPost;
import com.osamuharu.shared.port.TokenPost;
import java.time.Duration;
import java.time.Instant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutUseCase {

  private final TokenPost tokenPost;
  private final MemoryPost memoryPost;

  public void execute(String token) throws IllegalAccessException {
    String idToken = tokenPost.extractIdToken(token);

    if (idToken == null) {
      throw new IllegalAccessException("Invalid token");
    }

    Instant now = Instant.now();
    Instant expiration = tokenPost.extractExpiration(token);

    long expiresIn = Duration.between(now, expiration).getSeconds();

    if (expiresIn <= 0) {
      return;
    }

    memoryPost.saveTokenInBlacklist(idToken, expiresIn);
  }
}
