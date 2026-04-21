package com.osamuharu.core.mailer;

import com.osamuharu.shared.dto.SimpleMessageDto;
import com.osamuharu.shared.provider.MailerProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Validated
public class JavaMailerProvider implements MailerProvider {

  private final JavaMailSender javaMailSender;
  private final MailerProperties properties;

  @Override
  public void sendTextEmail(@Valid SimpleMessageDto dto) {

    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom(properties.getUsername());
    message.setTo(dto.getTo());
    message.setSubject(dto.getSubject());
    message.setText(dto.getText());

    javaMailSender.send(message);
  }

  @Override
  public void sendHtmlEmail(String to, String subject, String htmlBody) {

  }
}
