package com.zote.notification.adapter.strategy;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;

public interface NotificationStrategy {
    void sendNotification(NotificationData notificationData);
    NotificationType getType();  // Pour identifier quel type de notification est implémenté
}


