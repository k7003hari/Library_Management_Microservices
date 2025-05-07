package com.cts.service;

import java.time.LocalDateTime;
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
    public NotificationDTO sendNotification(NotificationDTO notificationDTO) {
        Notification notification = Notification.builder()
                .memberId(notificationDTO.getMemberId())
                .message(notificationDTO.getMessage())
                .dateSent(LocalDateTime.now())
                .build();
        Notification saved = notificationRepository.save(notification);
        return mapToDTO(saved);
    }
 
    @Override
    public List<NotificationDTO> getNotificationsByMemberId(Long memberId) {
        return notificationRepository.findByMemberId(memberId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public List<NotificationDTO> getAllNotifications() {
        return notificationRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public void deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new NotificationNotFoundException("Notification with ID " + notificationId + " not found.");
        }
        notificationRepository.deleteById(notificationId);
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