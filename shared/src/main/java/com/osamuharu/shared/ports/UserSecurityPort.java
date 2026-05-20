package com.osamuharu.shared.ports;

import com.osamuharu.shared.dtos.UserSecurityDto;
import java.util.Optional;

public interface UserSecurityPort {

  Optional<UserSecurityDto> loadUserByUsername(String username);
}
