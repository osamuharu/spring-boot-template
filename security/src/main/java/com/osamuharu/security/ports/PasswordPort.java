package com.osamuharu.security.ports;

public interface PasswordPort {

  String hashPassword(String password);

  boolean verifyPassword(String password, String hashedPassword);
}
