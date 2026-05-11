package com.osamuharu.notification.mailer.handler;

import com.osamuharu.notification.mailer.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationHandler {

  private final EmailSenderService emailSender;
  
}
