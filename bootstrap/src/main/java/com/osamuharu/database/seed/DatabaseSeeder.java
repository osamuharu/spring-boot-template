package com.osamuharu.database.seed;

import com.osamuharu.user.application.properties.UserProperties;
import com.osamuharu.user.application.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

  private final UserService userService;
  private final UserProperties userProperties;

  @Override
  @Transactional
  public void run(String @NonNull ... args) {
    seedUsers();
  }

  private void seedUsers() {
    userProperties.getDefaultUsers().forEach(user -> {
      try {
        userService.createUser(user);
        log.info("Seeded user: {}", user.getUsername());
      } catch (Exception e) {
        log.error("Failed to seed user: {}. Error: {}", user.getUsername(), e.getMessage());
      }
    });
  }

}
