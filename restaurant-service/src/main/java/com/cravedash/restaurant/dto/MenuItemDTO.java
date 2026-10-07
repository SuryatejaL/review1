package com.cravedash.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDTO {
    private Long id;
    private Long restaurantId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer availableQuantity;
    private Boolean isAvailable;
}
