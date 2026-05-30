package com.osamuharu.file.presentation.dto.response;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileDto {

  private String id;
  private String originalName;
  private String url;
  private String publicId;
  private Long size;
  private String format;
  private Instant createdAt;
  private Instant updatedAt;
}
