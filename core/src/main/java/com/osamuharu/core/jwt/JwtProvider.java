package com.osamuharu.core.jwt;

import com.osamuharu.shared.entity.Subject;
import com.osamuharu.shared.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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
  public String generateAccessToken(Subject subject) {
    return Jwts.builder()
        .subject(subject.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessTokenExpire()))
        .signWith(secretKey())
        .compact();
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
