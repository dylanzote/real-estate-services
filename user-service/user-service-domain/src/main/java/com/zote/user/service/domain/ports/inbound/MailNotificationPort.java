package com.zote.user.service.domain.ports.inbound;

public interface MailNotificationPort {
    void sendValidationEmail(String to);
}
