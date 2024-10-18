package com.zote.user.service.domain.usecase;

import com.zote.notification.adapter.NotificationContext;
import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.user.service.domain.ports.inbound.MailNotificationPort;
import com.zote.user.service.domain.ports.outbound.MailServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailNotificationService implements MailNotificationPort {

    private final MailServicePort mailServicePort;
    private final NotificationContext notificationContext;

    @Autowired
    public MailNotificationService(MailServicePort mailServicePort, NotificationContext notificationContext) {
        this.mailServicePort = mailServicePort;
        this.notificationContext = notificationContext;
    }

    @Override
    public void sendValidationEmail(String email) {
        NotificationData notificationData = new NotificationData(
                email,
                "Please Validate Your Email",
                "Thank you for registering. Please click the following link to verify your email: <VALIDATION_LINK>"
        );

        // Send the notification via email
        notificationContext.sendNotification(NotificationType.EMAIL, notificationData);
    }
}
