package com.cravedash.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDTO {

    private Long id;
    private Long orderId;
    private Long customerId;
    private BigDecimal amount;
    private String paymentMethod;
    private String paymentStatus;
    private LocalDateTime createdAt;
    private String transactionId;

    public PaymentDTO() {
    }

    public PaymentDTO(
            Long id,
            Long orderId,
            Long customerId,
            BigDecimal amount,
            String paymentMethod,
            String paymentStatus,
            LocalDateTime createdAt,
            String transactionId) {

        this.id = id;
        this.orderId = orderId;
        this.customerId = customerId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.createdAt = createdAt;
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}