package com.cravedash.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {
    
    @PostMapping("/payments/process")
    Object processPayment(@RequestBody Map<String, Object> paymentRequest);
}
