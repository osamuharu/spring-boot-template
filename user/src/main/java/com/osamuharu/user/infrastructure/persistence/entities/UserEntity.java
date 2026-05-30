package com.osamuharu.user.infrastructure.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osamuharu.file.infrastructure.persistence.entities.FileEntity;
import com.osamuharu.shared.entities.IdAutoIncrement;
import com.osamuharu.shared.entities.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends IdAutoIncrement {

  @Column(name = "first_name", length = 50, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 50, nullable = false)
  private String lastName;

  @Column(name = "username", length = 50, nullable = false, unique = true)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", length = 100, nullable = false)
  private String email;

  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "avatar_id", referencedColumnName = "id")
  private FileEntity avatar;

  @Embedded
  private Timestamp timestamp = new Timestamp();
}
