package com.osamuharu.notification.mailer.service;

import com.osamuharu.notification.mailer.properties.MailerProperties;
import com.osamuharu.shared.dto.SendMailMessageSimpleDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

  private final JavaMailSender javaMailSender;
  private final MailerProperties properties;

  public void sendTextEmail(@Valid SendMailMessageSimpleDto dto) {

    SimpleMailMessage message = new SimpleMailMessage();

    message.setFrom(properties.getUsername());
    message.setTo(dto.getTo());
    message.setSubject(dto.getSubject());
    message.setText(dto.getText());

    javaMailSender.send(message);
  }
}
