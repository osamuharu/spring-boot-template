package com.osamuharu.user.domain.entities;

import com.osamuharu.shared.entities.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

  private final Long id;
  private String email;
  private String username;
  private String firstName;
  private String lastName;
  private String password;
  private String avatarId;
  private Timestamp timestamp;

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

  public void changeAvatar(String avatarId) {
    if (avatarId.equals(this.avatarId)) {
      return;
    }

    this.avatarId = avatarId;
  }
}