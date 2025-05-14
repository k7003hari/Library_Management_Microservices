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

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

   private final NotificationService notificationService;

   @PostMapping
   public NotificationDTO sendNotification(@Valid @RequestBody NotificationDTO notificationDTO) {
       return notificationService.sendNotification(notificationDTO);
   }

   @GetMapping("/{memberId}")
   public List<NotificationDTO> getMemberNotifications(@PathVariable Long memberId) {
       return notificationService.getNotificationsForMember(memberId);
   }
}