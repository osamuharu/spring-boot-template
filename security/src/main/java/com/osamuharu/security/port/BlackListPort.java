package com.osamuharu.security.port;

public interface BlackListPort {

  void saveToken(String idToken, long expiresIn);

  boolean hasToken(String idToken);
}
