package com.zote.notification.adapter.strategy.impl;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationStrategy implements NotificationStrategy {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailNotificationStrategy(@Qualifier("javaMailSender") JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendNotification(NotificationData notificationData) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(notificationData.getRecipient());
        mailMessage.setSubject(notificationData.getSubject());
        mailMessage.setText(notificationData.getBody());
        mailSender.send(mailMessage);
    }

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }
}
