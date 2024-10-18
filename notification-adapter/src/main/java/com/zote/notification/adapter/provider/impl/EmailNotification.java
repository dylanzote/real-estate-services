package com.zote.notification.adapter.provider.impl;

import com.zote.common.utils.exceptions.FunctionalError;
import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.provider.Notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EmailNotification implements Notification {

    private final JavaMailSender mailSender;

    @Override
    public void sendNotification(NotificationData notificationData) {
        try {
            log.info("Sending email notification to {}", notificationData.getRecipient());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(notificationData.getRecipient());
            mailMessage.setSubject(notificationData.getSubject());
            mailMessage.setText(notificationData.getBody());

            mailSender.send(mailMessage);
            log.info("Sent notification successfully");
        } catch (Exception e) {
            throw new FunctionalError(e.getMessage());
        }
    }

    @Override
    public NotificationType getType() {
        return NotificationType.EMAIL;
    }
}
