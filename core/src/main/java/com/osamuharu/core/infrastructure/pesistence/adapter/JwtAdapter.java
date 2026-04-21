package com.osamuharu.core.infrastructure.pesistence.adapter;

import com.osamuharu.core.infrastructure.jwt.JwtProperties;
import com.osamuharu.shared.dto.PayloadDto;
import com.osamuharu.shared.dto.TokenDto;
import com.osamuharu.shared.port.TokenPost;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAdapter implements TokenPost {

  private final JwtProperties jwtProperties;

  private SecretKey secretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
  }

  @Override
  public TokenDto generateAccessToken(PayloadDto payloadDto) {
    Instant expiresIn = Instant.now().plus(jwtProperties.getAccessTokenExpiration());
    String jwtId = UUID.randomUUID().toString();

    String accessToken = Jwts.builder()
        .id(jwtId)
        .subject(payloadDto.getUsername())
        .issuedAt(Date.from(Instant.now()))
        .expiration(Date.from(expiresIn))
        .signWith(secretKey())
        .compact();

    return TokenDto.
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
  public PayloadDto extractPayload(String token) {
    if (isTokenInvalid(token)) {
      throw new JwtException("Invalid JWT token");
    }

    Claims claims = Jwts.parser()
        .verifyWith(secretKey())
        .build().parseSignedClaims(token)
        .getPayload();

    return PayloadDto.builder()
        .username(claims.getSubject())
        .build();
  }

  @Override
  public String extractIdToken(String token) {
    Claims claims = extractClaims(token);

    return claims.getId();
  }

  @Override
  public Instant extractExpiration(String token) {
    Claims claims = extractClaims(token);

    return claims.getExpiration().toInstant();
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
