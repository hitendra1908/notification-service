package com.api.freelancer.listener;

import com.api.freelancer.model.Notification;
import com.api.freelancer.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationKafkaListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "freelancer_notification", groupId = "freelancer-group")
    public void listenNotificationTopic(Notification notification) {
        log.info("Notification object coming in => {}", notification);
        notificationService.createNotification(notification);
    }
}
