package com.zote.notification.adapter;

import com.zote.notification.adapter.models.NotificationData;
import com.zote.notification.adapter.models.enums.NotificationType;
import com.zote.notification.adapter.strategy.NotificationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NotificationContext {

    private final List<NotificationStrategy> strategies;
    private Map<NotificationType, NotificationStrategy> strategyMap;

    @Autowired
    public NotificationContext(List<NotificationStrategy> strategies) {
        this.strategies = strategies;
    }

    // Initialize the Map after the constructor is called
    @PostConstruct
    public void init() {
        strategyMap = strategies.stream()
                .collect(Collectors.toMap(NotificationStrategy::getType, strategy -> strategy));
    }

    public void sendNotification(NotificationType type, NotificationData notificationData) {
        NotificationStrategy strategy = strategyMap.get(type);
        if (strategy != null) {
            strategy.sendNotification(notificationData);
        } else {
            throw new IllegalArgumentException("No strategy found for notification type: " + type);
        }
    }
}
