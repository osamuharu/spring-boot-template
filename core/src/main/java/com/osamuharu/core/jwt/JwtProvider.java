package com.osamuharu.core.jwt;

import com.osamuharu.core.properties.JwtProperties;
import com.osamuharu.shared.entity.Subject;
import com.osamuharu.shared.entity.Token;
import com.osamuharu.shared.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtProvider implements TokenProvider {

  private final JwtProperties jwtProperties;

  private SecretKey secretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
  }

  @Override
  public Token generateAccessToken(Subject subject) {
    Instant expiresIn = Instant.now().plus(jwtProperties.getAccessTokenExpire());

    String accessToken = Jwts.builder()
        .subject(subject.getUsername())
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(expiresIn))
        .signWith(secretKey())
        .compact();

    return Token.
        builder()
        .token(accessToken)
        .expiresIn(expiresIn.getEpochSecond())
        .build();
  }

  @Override
  public String extractUsername(String token) {
    Jws<Claims> parsedToken = Jwts.parser()
        .verifyWith(secretKey())
        .build()
        .parseSignedClaims(token);
    return parsedToken.getPayload()
        .getSubject();
  }

  @Override
  public boolean validateToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(secretKey())
          .build()
          .parseSignedClaims(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
