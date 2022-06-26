package dev.lotnest.microservices.notification.service;

import dev.lotnest.microservices.clients.notification.NotificationRequest;
import dev.lotnest.microservices.notification.model.Notification;
import dev.lotnest.microservices.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository notificationRepository) {

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .sender("Lotnest")
                        .message(notificationRequest.message())
                        .sentAt(LocalDateTime.now())
                        .build());
    }
}
