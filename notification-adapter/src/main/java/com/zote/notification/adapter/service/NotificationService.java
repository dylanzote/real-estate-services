package com.zote.notification.adapter.service;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.provider.NotificationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationProvider notificationProvider;

    public void sendNotification(NotificationData notificationData) {
        log.info("incoming  notification with data: {}", notificationData);
        var notification = notificationProvider.getNotification(Enum.valueOf(NotificationType.class, notificationData.getType()));
        notification.sendNotification(notificationData);
    }
}
