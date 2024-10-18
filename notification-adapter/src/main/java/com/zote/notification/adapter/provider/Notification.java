package com.zote.notification.adapter.provider;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;

public interface Notification {

    void sendNotification(NotificationData notificationData);

    NotificationType getType();
}


