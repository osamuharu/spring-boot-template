package com.osamuharu.security.ports;

import com.osamuharu.security.dtos.UserCredentialsDto;
import java.util.Optional;

public interface UserCredentialsPort {

  Optional<UserCredentialsDto> loadUserByUsername(String username);
}
