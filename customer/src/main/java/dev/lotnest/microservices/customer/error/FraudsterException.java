package dev.lotnest.microservices.customer.error;

public class FraudsterException extends RuntimeException {

    public FraudsterException(Long customerId) {
        super("Fraudster found with id " + customerId);
    }
}
