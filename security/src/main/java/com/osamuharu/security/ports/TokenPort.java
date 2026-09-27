package com.osamuharu.security.ports;


import com.osamuharu.security.dtos.PayloadDto;
import com.osamuharu.security.dtos.TokenDto;
import java.time.Instant;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenPort {

  TokenDto generateAccessToken(UserDetails userDetails);

  PayloadDto extractPayload(String token);

  String extractIdToken(String token);

  Instant extractExpiration(String token);

  boolean isValidToken(String token);
}
