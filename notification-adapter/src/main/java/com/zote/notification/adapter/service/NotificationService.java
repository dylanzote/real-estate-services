package com.zote.notification.adapter.service;

import com.zote.notification.adapter.NotificationContext;
import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationContext notificationContext;

    @Autowired
    public NotificationService(NotificationContext notificationContext) {
        this.notificationContext = notificationContext;
    }

    public void sendNotification(NotificationType type, NotificationData data) {
        notificationContext.sendNotification(type, data);
    }
}
