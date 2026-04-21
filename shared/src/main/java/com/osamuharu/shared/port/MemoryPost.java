package com.osamuharu.shared.port;

public interface MemoryPost {

  void saveTokenInBlacklist(String idToken, long expiresIn);

  boolean isTokenInBlackList(String idToken);
}
