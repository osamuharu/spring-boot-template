package com.osamuharu.security.port;

public interface PasswordPort {

  String hashPassword(String password);

  boolean verifyPassword(String password, String hashedPassword);
}
