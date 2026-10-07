package com.cravedash.payment.service;

import com.cravedash.payment.dto.PaymentDTO;
import com.cravedash.payment.dto.PaymentRequest;
import com.cravedash.payment.entity.Payment;
import com.cravedash.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public PaymentDTO processPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setOrderId(request.getOrderId());
        payment.setCustomerId(request.getCustomerId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setCreatedAt(LocalDateTime.now());
        
        // Simulate payment gateway processing
        try {
            // In real scenario, integrate with payment gateway (Stripe, PayPal, etc.)
            payment.setPaymentStatus("SUCCESS");
            payment.setTransactionId(UUID.randomUUID().toString());
        } catch (Exception e) {
            payment.setPaymentStatus("FAILED");
        }
        
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }
    
    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return convertToDTO(payment);
    }
    
    public PaymentDTO getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .stream()
                .map(this::convertToDTO)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Payment not found for order"));
    }
    
    private PaymentDTO convertToDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getOrderId(),
                payment.getCustomerId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getCreatedAt(),
                payment.getTransactionId()
        );
    }
}
