package com.osamuharu.security.ports;

import com.osamuharu.shared.dtos.UserDto;
import java.util.Optional;

public interface UserCredentialsPort {

  Optional<UserDto> loadUserByUsername(String username);

  Optional<UserDto> loadUserByEmail(String email);
}
