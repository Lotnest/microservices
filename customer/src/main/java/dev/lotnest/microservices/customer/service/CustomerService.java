package dev.lotnest.microservices.customer.service;

import dev.lotnest.microservices.clients.fraud.FraudCheckResponse;
import dev.lotnest.microservices.clients.fraud.FraudClient;
import dev.lotnest.microservices.clients.notification.NotificationClient;
import dev.lotnest.microservices.clients.notification.NotificationRequest;
import dev.lotnest.microservices.customer.error.FraudsterException;
import dev.lotnest.microservices.customer.model.Customer;
import dev.lotnest.microservices.customer.model.CustomerRegistrationRequest;
import dev.lotnest.microservices.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient,
                              NotificationClient notificationClient) {

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse != null && fraudCheckResponse.isFraudster()) {
            throw new FraudsterException(customer.getId());
        }

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to microservices course.", customer.getFirstName())));
    }
}
