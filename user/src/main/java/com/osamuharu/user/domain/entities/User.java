package com.osamuharu.user.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

  private final Long id;
  private String email;
  private String username;
  private String firstName;
  private String lastName;
  private String password;

  @Builder
  public User(Long id, String email, String username, String firstName, String lastName,
      String password) {
    this.id = id;
    this.email = email;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  public void changeEmail(String email) {
    validateEmail(email);

    if (email.equals(this.email)) {
      return;
    }

    this.email = email;
  }

  public void changeUsername(String username) {
    validateUsername(username);

    if (username.equals(this.username)) {
      return;
    }

    this.username = username;
  }

  public void changeFirstName(String firstName) {
    validateFirstName(firstName);

    if (firstName.equals(this.firstName)) {
      return;
    }

    this.firstName = firstName;
  }

  public void changeLastName(String lastName) {
    validateLastName(lastName);

    if (lastName.equals(this.lastName)) {
      return;
    }

    this.lastName = lastName;
  }

  public void changePassword(String password) {
    validatePassword(password);

    this.password = password;
  }

  private void validateEmail(String email) {
    if (email == null || email.trim()
        .isEmpty()) {
      throw new IllegalArgumentException("Email not be blank");
    }

    if (!email.contains("@")) {
      throw new IllegalArgumentException("Email invalid: " + email);
    }
  }

  private void validateUsername(String username) {
    if (username == null || username.trim()
        .isEmpty()) {
      throw new IllegalArgumentException("Username not be blank");
    }

    int length = username.length();

    if (!username.matches("^[a-zA-Z0-9._-]+$")) {
      throw new IllegalArgumentException(
          "Username can only contain letters, numbers, dots, hyphens, and " +
              "underscores: " + username);
    }

    if (length < 3 || length > 50) {
      throw new IllegalArgumentException(
          "Username must be between 3 and 50 characters: " + username);
    }
  }

  private void validateFirstName(String firstName) {
    if (firstName == null || firstName.trim()
        .isEmpty()) {
      throw new IllegalArgumentException("First name not be blank");
    }

    int length = firstName.length();

    if (length > 50) {
      throw new IllegalArgumentException("First name must not exceed 50 characters: " + firstName);
    }
  }

  private void validateLastName(String lastName) {
    if (lastName == null || lastName.trim()
        .isEmpty()) {
      throw new IllegalArgumentException("Last name not be blank");
    }

    int length = lastName.length();

    if (length > 50) {
      throw new IllegalArgumentException("Last name must not exceed 50 characters: " + lastName);
    }
  }

  private void validatePassword(String password) {
    if (password == null || password.trim()
        .isEmpty()) {
      throw new IllegalArgumentException("Password not be blank");
    }

    int length = password.length();

    if (length < 8 || length > 255) {
      throw new IllegalArgumentException(
          "Password must be between 8 and 255 characters: " + password);
    }
  }
}