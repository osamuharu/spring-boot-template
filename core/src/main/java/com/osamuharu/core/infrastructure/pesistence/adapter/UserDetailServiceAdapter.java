package com.osamuharu.core.infrastructure.pesistence.adapter;

import com.osamuharu.shared.dto.UserSecurityDto;
import com.osamuharu.shared.port.UserSecurityPort;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailServiceAdapter implements UserDetailsService {

  private final UserSecurityPort userSecurityPort;

  @Override
  @NonNull
  public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

    UserSecurityDto userDto = userSecurityPort.loadUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    Set<GrantedAuthority> authorities = new HashSet<>();

    return new User(
        userDto.getUsername(),
        userDto.getPassword(),
        authorities
    );
  }
}
