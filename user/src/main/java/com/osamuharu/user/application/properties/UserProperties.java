package com.osamuharu.user.application.properties;

import com.osamuharu.user.presentation.dto.requests.CreateUserDto;
import jakarta.validation.Valid;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.user")
@Validated
public class UserProperties {

  private List<@Valid CreateUserDto> defaultUsers;
}
