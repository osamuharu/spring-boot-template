package com.osamuharu.shared.provider;

import com.osamuharu.shared.entity.Subject;

public interface TokenProvider {
	String generateAccessToken(Subject subject);
	
	String extractUsername(String token);
	
	boolean validateToken(String token);
}
