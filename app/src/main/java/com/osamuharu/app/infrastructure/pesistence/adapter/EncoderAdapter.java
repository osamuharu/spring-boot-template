package com.osamuharu.app.infrastructure.pesistence.adapter;

import com.osamuharu.shared.port.PasswordPost;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncoderAdapter implements PasswordPost {

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
