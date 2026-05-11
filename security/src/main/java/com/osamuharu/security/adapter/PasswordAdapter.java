package com.osamuharu.security.adapter;

import com.osamuharu.security.port.PasswordPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordAdapter implements PasswordPort {

  private final PasswordEncoder encoder;

  @Override
  public String hashPassword(String password) {
    return encoder.encode(password);
  }

  @Override
  public boolean verifyPassword(String password, String hashedPassword) {
    return encoder.matches(password, hashedPassword);
  }
}
