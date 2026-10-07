package com.cravedash.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO {

    private Long id;
    private Long customerId;
    private Long restaurantId;
    private String orderStatus;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long deliveryPersonId;

    public OrderDTO() {
    }

    public OrderDTO(
            Long id,
            Long customerId,
            Long restaurantId,
            String orderStatus,
            BigDecimal totalAmount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long deliveryPersonId) {

        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deliveryPersonId = deliveryPersonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(Long deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }
}