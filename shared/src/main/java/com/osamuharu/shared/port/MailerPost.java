package com.osamuharu.shared.port;

import com.osamuharu.shared.dto.SimpleMessageDto;
import jakarta.validation.Valid;

public interface MailerPost {

  void sendTextEmail(@Valid SimpleMessageDto messageDto);
}
