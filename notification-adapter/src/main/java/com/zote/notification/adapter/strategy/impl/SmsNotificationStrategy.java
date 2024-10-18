package com.zote.notification.adapter.strategy.impl;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.strategy.NotificationStrategy;
import org.springframework.stereotype.Service;

@Service
public class SmsNotificationStrategy implements NotificationStrategy {

    @Override
    public void sendNotification(NotificationData notificationData) {
        // Implement SMS sending logic here
        System.out.println("Sending SMS to " + notificationData.getRecipient() +
                ": " + notificationData.getBody());
    }

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }
}
