package com.osamuharu.security.ports;

import org.springframework.security.core.Authentication;

public interface InternalSecurityPort {

  Authentication getCurrentAuthentication();

  void setContextAsUser(String username);
}
