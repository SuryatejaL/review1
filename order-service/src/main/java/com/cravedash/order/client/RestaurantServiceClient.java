package com.cravedash.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service")
public interface RestaurantServiceClient {
    
    @GetMapping("/restaurants/{id}")
    Object getRestaurant(@PathVariable Long id);
    
    @GetMapping("/restaurants/{restaurantId}/menu/{itemId}")
    Object getMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId);
}
