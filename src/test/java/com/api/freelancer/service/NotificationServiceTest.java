package com.api.freelancer.service;

import com.api.freelancer.entity.NotificationEntity;
import com.api.freelancer.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @InjectMocks
    NotificationService notificationService;

    @Mock
    NotificationRepository notificationRepository;

    @Test
    void getNotificationsByUser() {
        var notificationEntity = NotificationEntity.builder()
                .receiver("testUser")
                .documentName("testDoc")
                .timestamp(LocalDateTime.now())
                .build();
        var list = List.of(notificationEntity);

        when(notificationRepository.findByReceiver("testUSer")).thenReturn(list);
        assertEquals(list, notificationService.getNotificationByUser("testUSer"));
    }
}