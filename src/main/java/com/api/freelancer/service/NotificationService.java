package com.api.freelancer.service;

import com.api.freelancer.entity.NotificationEntity;
import com.api.freelancer.model.Notification;
import com.api.freelancer.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void createNotification(Notification notification) {
        NotificationEntity notificationEntity = NotificationEntity.builder()
                        .receiver(notification.getReceiver())
                        .documentName(notification.getDocumentName())
                        .timestamp(notification.getTimestamp())
                        .build();

        notificationRepository.save(notificationEntity);
        log.info("notification saved in database --> {}", notificationEntity);
    }

    public List<NotificationEntity> getNotificationByUser(String userName) {
        return notificationRepository.findByReceiver(userName);
    }
}
