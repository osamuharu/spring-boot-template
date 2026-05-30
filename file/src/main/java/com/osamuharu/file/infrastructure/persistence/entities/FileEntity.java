package com.osamuharu.file.infrastructure.persistence.entities;

import com.osamuharu.shared.entities.IdUUID;
import com.osamuharu.shared.entities.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
@NoArgsConstructor
public class FileEntity extends IdUUID {

  @Column(name = "original_name")
  private String originalName;

  private String url;

  @Column(name = "public_id")
  private String publicId;

  private Long size;

  private String format;

  @Embedded
  private Timestamp timestamp = new Timestamp();
}
