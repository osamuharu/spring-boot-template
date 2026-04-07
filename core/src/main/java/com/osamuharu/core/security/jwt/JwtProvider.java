package com.osamuharu.core.security.jwt;

import com.osamuharu.shared.entity.Subject;
import com.osamuharu.shared.provider.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

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
