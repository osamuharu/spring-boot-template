package com.osamuharu.shared.port;

import com.osamuharu.shared.dto.UserSecurityDto;
import java.util.Optional;

public interface UserSecurityPort {

  Optional<UserSecurityDto> loadUserByUsername(String username);
}
