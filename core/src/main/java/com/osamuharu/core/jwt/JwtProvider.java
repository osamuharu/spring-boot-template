package com.osamuharu.core.jwt;

import com.osamuharu.core.properties.JwtProperties;
import com.osamuharu.shared.entity.Payload;
import com.osamuharu.shared.entity.Token;
import com.osamuharu.shared.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
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
  public Token generateAccessToken(Payload payload) {
    Instant expiresIn = Instant.now().plus(jwtProperties.getAccessTokenExpire());

    String accessToken = Jwts.builder()
        .subject(payload.getUsername())
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

  public Claims extractClaims(String token) {
    if (isTokenInvalid(token)) {
      throw new JwtException("Invalid JWT token");
    }

    return Jwts.parser()
        .verifyWith(secretKey())
        .build().parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public Payload extractPayload(String token) {
    if (isTokenInvalid(token)) {
      throw new JwtException("Invalid JWT token");
    }

    Claims claims = Jwts.parser()
        .verifyWith(secretKey())
        .build().parseSignedClaims(token)
        .getPayload();

    return Payload.builder()
        .username(claims.getSubject())
        .build();
  }


  @Override
  public boolean isTokenInvalid(String token) {
    try {
      Jwts.parser()
          .verifyWith(secretKey())
          .build()
          .parseSignedClaims(token);
      return false;
    } catch (Exception e) {
      return true;
    }
  }
}
