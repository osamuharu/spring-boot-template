package com.osamuharu.shared.provider;

import com.osamuharu.shared.dto.SimpleMessageDto;
import jakarta.validation.Valid;

public interface MailerProvider {

  void sendTextEmail(@Valid SimpleMessageDto message);

  void sendHtmlEmail(String to, String subject, String htmlBody);
}
