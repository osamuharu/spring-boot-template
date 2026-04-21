package com.osamuharu.shared.port;

public interface PasswordPost {

  String hashPassword(String password);

  boolean verifyPassword(String password, String hashedPassword);
}
