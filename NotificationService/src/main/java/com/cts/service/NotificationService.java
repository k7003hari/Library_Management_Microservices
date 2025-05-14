package com.cts.service;

import java.util.List;

import com.cts.dto.NotificationDTO;

public interface NotificationService {

	NotificationDTO sendNotification(NotificationDTO notificationDTO);

	List<NotificationDTO> getNotificationsForMember(Long memberId);
}