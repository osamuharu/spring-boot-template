package com.osamuharu.file.infrastructure.cloudinary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CloudinaryResponse {

  private String publicId;
  private String url;
}
