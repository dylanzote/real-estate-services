package com.zote.notification.adapter.strategy.impl;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.strategy.NotificationStrategy;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationStrategy implements NotificationStrategy {

    @Override
    public void sendNotification(NotificationData notificationData) {
        // Implement Push Notification sending logic here
        System.out.println("Sending Push Notification to " + notificationData.getRecipient() +
                ": " + notificationData.getBody());
    }

    @Override
    public NotificationType getType() {
        return NotificationType.PUSH;
    }
}
