package com.zote.notification.adapter;


import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationContext {

    private final List<NotificationStrategy> strategies;

    @Autowired
    public NotificationContext(List<NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    public void sendNotification(NotificationType type, NotificationData notificationData) {
        for (NotificationStrategy strategy : strategies) {
            if (strategy.getType() == type) {
                strategy.sendNotification(notificationData);
                return;
            }
        }
        throw new IllegalArgumentException("No strategy found for notification type: " + type);
    }
}