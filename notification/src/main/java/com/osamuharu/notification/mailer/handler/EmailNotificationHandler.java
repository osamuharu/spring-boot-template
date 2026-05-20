package com.osamuharu.notification.mailer.handler;

import com.osamuharu.notification.mailer.service.EmailSenderService;
import com.osamuharu.shared.dtos.SendMailMessageSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationHandler {

  private final EmailSenderService emailSender;

  @ApplicationModuleListener
  public void handleTextEmailNotification(SendMailMessageSimpleDto dto) {
    emailSender.sendTextEmail(dto);
  }
}


