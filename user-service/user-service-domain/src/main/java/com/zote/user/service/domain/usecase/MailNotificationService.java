package com.zote.user.service.domain.usecase;

import com.zote.user.service.domain.ports.inbound.MailNotificationPort;
import com.zote.user.service.domain.ports.outbound.MailServicePort;
import org.springframework.stereotype.Service;

@Service
public class MailNotificationService implements MailNotificationPort {

    private final MailServicePort mailServicePort;

    public MailNotificationService(MailServicePort mailServicePort) {
        this.mailServicePort = mailServicePort;
    }

    @Override
    public void sendValidationEmail(String to) {
        String subject = "Email Validation";
        String content = "<h1>Please validate your email</h1>";
        mailServicePort.sendEmail(to, subject, content);
    }
}
