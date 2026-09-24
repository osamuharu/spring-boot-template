package com.osamuharu.security.adapters;

import com.osamuharu.security.ports.UserCredentialsPort;
import com.osamuharu.shared.dtos.UserDto;
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

  private final UserCredentialsPort userCredentialsPort;

  @Override
  @NonNull
  public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {

    UserDto userDto = userCredentialsPort.loadUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    Set<GrantedAuthority> authorities = new HashSet<>();

    return new User(
        userDto.getUsername(),
        userDto.getPassword(),
        authorities
    );
  }
}
