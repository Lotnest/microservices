package dev.lotnest.microservices.customer.repository;

import dev.lotnest.microservices.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
