package dev.lotnest.microservices.fraud.controller;

import dev.lotnest.microservices.clients.fraud.FraudCheckResponse;
import dev.lotnest.microservices.fraud.service.FraudCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public record FraudController(FraudCheckService fraudCheckService) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Long customerId) {
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Fraud check for customer {}: {}", customerId, isFraudulentCustomer);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
