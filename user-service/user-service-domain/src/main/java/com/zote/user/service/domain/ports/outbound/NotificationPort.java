package com.zote.user.service.domain.ports.outbound;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationPort {

    private final NotificationService notificationService;
    public void sendEmailNotification(String email, String subject, String body) {
        var notificationData = NotificationData.builder()
                .recipient(email)
                .subject(subject)
                .body(body)
                .type("EMAIL")
                .build();
        notificationService.sendNotification(notificationData);
    }
}
