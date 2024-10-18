package com.zote.notification.adapter.provider.impl;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.provider.Notification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsNotification implements Notification {

    @Override
    public void sendNotification(NotificationData notificationData) {
        log.info("sms notification not yet implemented");
    }

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }
}
