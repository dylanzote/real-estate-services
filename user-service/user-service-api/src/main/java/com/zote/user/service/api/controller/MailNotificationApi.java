package com.zote.user.service.api.controller;

import com.zote.user.service.domain.ports.inbound.MailNotificationPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MailNotificationApi {

    private final MailNotificationPort mailNotificationPort;

    public MailNotificationApi(MailNotificationPort mailNotificationPort) {
        this.mailNotificationPort = mailNotificationPort;
    }

    @GetMapping("/send-validation-email")
    public String sendValidationEmail(@RequestParam String email) {
        mailNotificationPort.sendValidationEmail(email);
        return "Validation email sent!";
    }
}
