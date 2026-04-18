package com.osamuharu.shared.utils;

public class TokenUtils {

  public static String extractTokenFromHeader(String header) {
    if (header == null || !header.startsWith("Bearer ")) {
      return null;
    }
    return header.substring(7);
  }
}
