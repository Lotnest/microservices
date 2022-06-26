package dev.lotnest.microservices.clients.notification;

public record NotificationRequest(Long toCustomerId, String toCustomerEmail, String message) {
}