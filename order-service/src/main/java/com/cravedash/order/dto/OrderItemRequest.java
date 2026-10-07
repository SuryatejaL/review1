package com.cravedash.order.dto;

import java.math.BigDecimal;

public class OrderItemRequest {

    private Long menuItemId;
    private Integer quantity;
    private BigDecimal price;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Long menuItemId, Integer quantity, BigDecimal price) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}