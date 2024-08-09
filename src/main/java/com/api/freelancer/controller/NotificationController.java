package com.api.freelancer.controller;

import com.api.freelancer.entity.NotificationEntity;
import com.api.freelancer.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/notifications/{userName}")
    public List<NotificationEntity> getNotification(@PathVariable(name="userName") String userName){
        return notificationService.getNotificationByUser(userName);
    }
}
