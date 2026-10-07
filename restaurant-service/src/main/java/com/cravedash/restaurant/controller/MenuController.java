package com.cravedash.restaurant.controller;

import com.cravedash.restaurant.dto.MenuItemDTO;
import com.cravedash.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menu")
@CrossOrigin("*")
public class MenuController {
    
    @Autowired
    private MenuService menuService;
    
    @GetMapping
    public ResponseEntity<List<MenuItemDTO>> getMenu(@PathVariable Long restaurantId) {
        List<MenuItemDTO> menu = menuService.getMenuByRestaurantId(restaurantId);
        return ResponseEntity.ok(menu);
    }
    
    @GetMapping("/{itemId}")
    public ResponseEntity<MenuItemDTO> getMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        MenuItemDTO item = menuService.getMenuItemById(itemId);
        return ResponseEntity.ok(item);
    }
    
    @PostMapping
    public ResponseEntity<MenuItemDTO> addMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItemDTO dto) {
        dto.setRestaurantId(restaurantId);
        MenuItemDTO created = menuService.addMenuItem(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{itemId}")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId, @RequestBody MenuItemDTO dto) {
        MenuItemDTO updated = menuService.updateMenuItem(itemId, dto);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long restaurantId, @PathVariable Long itemId) {
        menuService.deleteMenuItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
