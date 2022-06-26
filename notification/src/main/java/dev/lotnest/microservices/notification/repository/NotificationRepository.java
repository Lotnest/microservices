package dev.lotnest.microservices.notification.repository;

import dev.lotnest.microservices.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
