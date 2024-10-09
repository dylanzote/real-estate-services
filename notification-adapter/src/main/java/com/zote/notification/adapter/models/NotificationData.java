package com.zote.notification.adapter.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationData {
    private String recipient;
    private String subject;   // Applicable pour l'email, pas nécessaire pour SMS
    private String body;
}
