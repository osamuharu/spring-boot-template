package com.osamuharu.user.domain.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
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
    if (email.equals(this.email)) {
      return;
    }

    this.email = email;
  }

  public void changeUsername(String username) {
    if (username.equals(this.username)) {
      return;
    }

    this.username = username;
  }

  public void changeFirstName(String firstName) {
    if (firstName.equals(this.firstName)) {
      return;
    }

    this.firstName = firstName;
  }

  public void changeLastName(String lastName) {
    if (lastName.equals(this.lastName)) {
      return;
    }

    this.lastName = lastName;
  }

  public void changePassword(String password) {
    this.password = password;
  }
}