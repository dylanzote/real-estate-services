package com.zote.common.utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class BeanConfig {

    // Bean pour encoder les mots de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean pour envoyer des emails avec JavaMailSender
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");  // Adresse du serveur SMTP
        mailSender.setPort(587);  // Port SMTP standard pour TLS

        // Configuration des identifiants SMTP (remplace par les identifiants de l'app)
        mailSender.setUsername("the-email@gmail.com");
        mailSender.setPassword("the-email-password");

        // property additionnelles pour le protocole SMTP
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");  // Pour activer les logs de débogage SMTP

        return mailSender;
    }
}
