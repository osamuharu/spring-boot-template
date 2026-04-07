package com.osamuharu.core.security.handler;

import com.osamuharu.user.domain.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	private final UserRepository repository;
	
	@Override
	@NonNull
	public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
		
		com.osamuharu.user.domain.entity.User user = repository.findByUsername(username)
		                                                       .orElse(null);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		return new User(
				user.getUsername(),
				user.getPassword(),
				authorities
		);
	}
}
