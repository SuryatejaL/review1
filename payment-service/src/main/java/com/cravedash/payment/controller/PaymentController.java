package com.cravedash.payment.controller;

import com.cravedash.payment.dto.PaymentDTO;
import com.cravedash.payment.dto.PaymentRequest;
import com.cravedash.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {
    
    @Autowired
    private PaymentService paymentService;
    
    @PostMapping("/process")
    public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentRequest request) {
        PaymentDTO payment = paymentService.processPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id) {
        PaymentDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentDTO> getPaymentByOrderId(@PathVariable Long orderId) {
        PaymentDTO payment = paymentService.getPaymentByOrderId(orderId);
        return ResponseEntity.ok(payment);
    }
}
