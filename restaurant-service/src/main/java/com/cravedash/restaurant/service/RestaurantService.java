package com.cravedash.restaurant.service;

import com.cravedash.restaurant.dto.RestaurantDTO;
import com.cravedash.restaurant.entity.Restaurant;
import com.cravedash.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;
    
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public RestaurantDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return convertToDTO(restaurant);
    }
    
    public RestaurantDTO createRestaurant(RestaurantDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhone(dto.getPhone());
        restaurant.setMinimumOrderValue(dto.getMinimumOrderValue());
        restaurant.setIsOpen(dto.getIsOpen());
        restaurant.setRating(dto.getRating());
        
        Restaurant saved = restaurantRepository.save(restaurant);
        return convertToDTO(saved);
    }
    
    public RestaurantDTO updateRestaurant(Long id, RestaurantDTO dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhone(dto.getPhone());
        restaurant.setMinimumOrderValue(dto.getMinimumOrderValue());
        restaurant.setIsOpen(dto.getIsOpen());
        restaurant.setRating(dto.getRating());
        
        Restaurant updated = restaurantRepository.save(restaurant);
        return convertToDTO(updated);
    }
    
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
    
    private RestaurantDTO convertToDTO(Restaurant restaurant) {
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getMinimumOrderValue(),
                restaurant.getIsOpen(),
                restaurant.getRating()
        );
    }
}
