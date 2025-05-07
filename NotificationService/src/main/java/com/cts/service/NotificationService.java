package com.cts.service;

import java.util.List;

import com.cts.dto.NotificationDTO;

public interface NotificationService {
    NotificationDTO sendNotification(NotificationDTO notificationDTO);
    List<NotificationDTO> getNotificationsByMemberId(Long memberId);
    List<NotificationDTO> getAllNotifications();
    void deleteNotification(Long notificationId);
}