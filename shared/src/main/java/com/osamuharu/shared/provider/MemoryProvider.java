package com.osamuharu.shared.provider;

public interface MemoryProvider {

  void saveTokenInBlacklist(String idToken, long expiresIn);

  boolean isTokenInBlackList(String idToken);
}
