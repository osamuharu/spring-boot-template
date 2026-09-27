package com.osamuharu.security.ports;

import org.springframework.security.core.userdetails.UserDetails;

public interface InternalSecurityPort {

  void setContextAsUser(UserDetails userDetails, String token);

  String getCurrentToken();

  UserDetails getCurrentUser();
}
