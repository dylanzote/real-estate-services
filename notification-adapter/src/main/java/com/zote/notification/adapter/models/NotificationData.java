package com.zote.notification.adapter.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationData {
    private String recipient;
    private String subject;
    private String body;
    private String type;
}
