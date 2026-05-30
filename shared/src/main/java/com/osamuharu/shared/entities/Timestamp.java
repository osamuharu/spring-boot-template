package com.osamuharu.shared.entities;

import jakarta.persistence.Embeddable;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Embeddable
@Getter
@Setter
public class Timestamp {

  @CreationTimestamp
  private Instant createdAt;

  @UpdateTimestamp
  private Instant updatedAt;

  private Instant deletedAt;
}
