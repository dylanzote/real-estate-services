package com.zote.notification.adapter.provider;

import com.zote.notification.adapter.models.enums.NotificationType;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class NotificationProvider {

    private final Map<NotificationType, Notification> notificationMap = new EnumMap<>(NotificationType.class);

    public NotificationProvider(List<Notification> notifications) {
        notifications.forEach(notification -> notificationMap.put(notification.getType(), notification));
    }

    public Notification getNotification(NotificationType type) {
        return notificationMap.get(type);
    }
}
