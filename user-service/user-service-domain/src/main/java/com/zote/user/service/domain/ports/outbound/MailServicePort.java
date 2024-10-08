package com.zote.user.service.domain.ports.outbound;

    public interface MailServicePort {
        void sendEmail(String to, String subject, String content);
    }

