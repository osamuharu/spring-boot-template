package com.osamuharu.file.domain.entities;

import com.osamuharu.shared.entities.Timestamp;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class File {

  private final String id;
  private String originalName;
  private String url;
  private String publicId;
  private Long size;
  private String format;
  private Timestamp timestamp;

  @Override
  public boolean equals(Object obj) {
    return this == obj || (obj instanceof File && id != null && id.equals(((File) obj).id));
  }
}
