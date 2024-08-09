package com.api.freelancer.controller;

import com.api.freelancer.entity.NotificationEntity;
import com.api.freelancer.service.NotificationService;
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
class NotificationControllerTest {

    @InjectMocks
    NotificationController notificationController;

    @Mock
    NotificationService notificationService;

    @Test
    void getNotification() {
        var notificationEntity = NotificationEntity.builder()
                .receiver("testUser")
                .documentName("testDoc")
                .timestamp(LocalDateTime.now())
                .build();
        var list = List.of(notificationEntity);

        when(notificationService.getNotificationByUser("testUSer")).thenReturn(list);
        assertEquals(list, notificationController.getNotification("testUSer"));
    }
}