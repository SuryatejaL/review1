package com.cravedash.restaurant.controller;

import com.cravedash.restaurant.dto.RestaurantDTO;
import com.cravedash.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin("*")
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;
    
    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }
    
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO dto) {
        RestaurantDTO created = restaurantService.createRestaurant(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDTO dto) {
        RestaurantDTO updated = restaurantService.updateRestaurant(id, dto);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }
}
