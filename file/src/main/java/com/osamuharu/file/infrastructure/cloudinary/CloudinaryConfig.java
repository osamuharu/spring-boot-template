package com.osamuharu.file.infrastructure.cloudinary;

import com.cloudinary.Cloudinary;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CloudinaryConfig {

  private final CloudinaryProperties properties;

  @Bean
  public Cloudinary cloudinary() {
    Map<String, String> config = new HashMap<>();
    config.put("cloud_name", properties.getCloudName());
    config.put("api_key", properties.getApiKey());
    config.put("api_secret", properties.getApiSecret());
    return new Cloudinary(config);
  }
}
