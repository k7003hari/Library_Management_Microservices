package com.cts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.dto.NotificationDTO;
import com.cts.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
 
    private final NotificationService notificationService;
 
    @PostMapping
    public ResponseEntity<NotificationDTO> sendNotification(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(notificationService.sendNotification(dto));
    }
 
    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<NotificationDTO>> getByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(notificationService.getNotificationsByMemberId(memberId));
    }
 
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
 
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}