package com.cts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.NotificationDTO;
import com.cts.service.NotificationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public NotificationDTO sendNotification(@Valid @RequestBody NotificationDTO notificationDTO) {
        log.info("POST /notifications/send");
        return notificationService.sendNotification(notificationDTO);
    }

    @GetMapping("/{memberId}")
    public List<NotificationDTO> getMemberNotifications(@PathVariable Long memberId) {
        log.info("GET /notifications/{}", memberId);
        return notificationService.getNotificationsForMember(memberId);
    }
}
