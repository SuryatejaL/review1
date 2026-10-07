package com.cravedash.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private BigDecimal minimumOrderValue;
    private Boolean isOpen;
    private Float rating;
}
