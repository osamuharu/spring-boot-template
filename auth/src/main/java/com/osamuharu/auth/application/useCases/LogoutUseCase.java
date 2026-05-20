package com.osamuharu.auth.application.useCases;

import com.osamuharu.security.ports.BlackListPort;
import com.osamuharu.security.ports.TokenPort;
import java.time.Duration;
import java.time.Instant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogoutUseCase {

  private final TokenPort tokenPost;
  private final BlackListPort blackListPort;

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

    blackListPort.saveToken(idToken, expiresIn);
  }
}
