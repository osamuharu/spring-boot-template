package com.osamuharu.security.adapters;

import com.osamuharu.security.dtos.PayloadDto;
import com.osamuharu.security.dtos.TokenDto;
import com.osamuharu.security.ports.TokenPort;
import com.osamuharu.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAdapter implements TokenPort {

  private final JwtProperties jwtProperties;

  private SecretKey secretKey() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret()));
  }

  @Override
  public TokenDto generateAccessToken(Authentication authentication)
      throws UserPrincipalNotFoundException {
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();

    if (userDetails == null) {
      throw new UserPrincipalNotFoundException("User principal not found in authentication object");
    }

    Instant expiresIn = Instant.now().plus(jwtProperties.getAccessTokenExpiration());
    String jwtId = UUID.randomUUID().toString();

    String accessToken = Jwts.builder()
        .id(jwtId)
        .subject(userDetails.getUsername())
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
    return Jwts.parser()
        .verifyWith(secretKey())
        .build().parseSignedClaims(token)
        .getPayload();
  }

  @Override
  public PayloadDto extractPayload(String token) {
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
  public boolean isValidToken(String token) {
    try {
      Jwts.parser()
          .verifyWith(secretKey())
          .build()
          .parseSignedClaims(token);
      return true;
    } catch (MalformedJwtException e) {
      throw new JwtException("Invalid JWT Token: " + e.getMessage());
    } catch (ExpiredJwtException e) {
      throw new JwtException("JWT Token is Expired: " + e.getMessage());
    } catch (UnsupportedJwtException e) {
      throw new JwtException("Unsupported JWT: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      throw new JwtException("JWT Payload is Empty: " + e.getMessage());
    }
  }
}
