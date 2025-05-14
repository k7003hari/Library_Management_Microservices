package com.cts.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cts.dto.NotificationDTO;
import com.cts.exception.NotificationNotFoundException;
import com.cts.model.Notification;
import com.cts.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
 
    private final NotificationRepository notificationRepository;
 
    @Override
    public NotificationDTO sendNotification(NotificationDTO dto) {
        Notification notification = Notification.builder()
                .memberId(dto.getMemberId())
                .message(dto.getMessage())
                .dateSent(LocalDate.now())
                .build();
 
        Notification saved = notificationRepository.save(notification);
        return mapToDTO(saved);
    }
 
    @Override
    public List<NotificationDTO> getNotificationsForMember(Long memberId) {
        List<Notification> notifications = notificationRepository.findByMemberId(memberId);
        if (notifications.isEmpty()) {
            throw new NotificationNotFoundException("No notifications for member ID: " + memberId);
        }
        return notifications.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
 
    private NotificationDTO mapToDTO(Notification notification) {
        return NotificationDTO.builder()
                .notificationId(notification.getNotificationId())
                .memberId(notification.getMemberId())
                .message(notification.getMessage())
                .dateSent(notification.getDateSent())
                .build();
    }
}