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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;

	@Override
	public NotificationDTO sendNotification(NotificationDTO dto) {
		log.info("Sending notification to member ID: {}", dto.getMemberId());
		Notification notification = Notification.builder().memberId(dto.getMemberId()).message(dto.getMessage())
				.dateSent(LocalDate.now()).build();

		Notification saved = notificationRepository.save(notification);
		log.info("Notification sent successfully to member ID: {}", dto.getMemberId());
		return new NotificationDTO(saved.getNotificationId(), saved.getMemberId(), saved.getMessage(),
				saved.getDateSent());
	}

	@Override
	public List<NotificationDTO> getNotificationsForMember(Long memberId) {
		log.debug("Fetching notifications for member ID: {}", memberId);
		List<Notification> notifications = notificationRepository.findByMemberId(memberId);
		if (notifications.isEmpty()) {
			log.error("No notifications found for member ID: {}", memberId);
			throw new NotificationNotFoundException("No notifications for member ID: " + memberId);
		}
		log.info("Retrieved {} notifications for member ID: {}", notifications.size(), memberId);
		return notifications
				.stream().map(notification -> new NotificationDTO(notification.getNotificationId(),
						notification.getMemberId(), notification.getMessage(), notification.getDateSent()))
				.collect(Collectors.toList());
	}
}
