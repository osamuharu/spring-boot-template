package com.osamuharu.security.ports;

public interface BlackListPort {

  void saveToken(String idToken, long expiresIn);

  boolean hasToken(String idToken);
}
