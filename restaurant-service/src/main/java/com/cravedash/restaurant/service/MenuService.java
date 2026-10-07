package com.cravedash.restaurant.service;

import com.cravedash.restaurant.dto.MenuItemDTO;
import com.cravedash.restaurant.entity.MenuItem;
import com.cravedash.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {
    
    @Autowired
    private MenuItemRepository menuItemRepository;
    
    public List<MenuItemDTO> getMenuByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public MenuItemDTO getMenuItemById(Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        return convertToDTO(menuItem);
    }
    
    public MenuItemDTO addMenuItem(MenuItemDTO dto) {
        MenuItem menuItem = new MenuItem();
        menuItem.setRestaurantId(dto.getRestaurantId());
        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        menuItem.setAvailableQuantity(dto.getAvailableQuantity());
        menuItem.setIsAvailable(dto.getIsAvailable());
        
        MenuItem saved = menuItemRepository.save(menuItem);
        return convertToDTO(saved);
    }
    
    public MenuItemDTO updateMenuItem(Long id, MenuItemDTO dto) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));
        
        menuItem.setName(dto.getName());
        menuItem.setDescription(dto.getDescription());
        menuItem.setPrice(dto.getPrice());
        menuItem.setAvailableQuantity(dto.getAvailableQuantity());
        menuItem.setIsAvailable(dto.getIsAvailable());
        
        MenuItem updated = menuItemRepository.save(menuItem);
        return convertToDTO(updated);
    }
    
    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
    
    private MenuItemDTO convertToDTO(MenuItem menuItem) {
        return new MenuItemDTO(
                menuItem.getId(),
                menuItem.getRestaurantId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getAvailableQuantity(),
                menuItem.getIsAvailable()
        );
    }
}
