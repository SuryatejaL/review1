package com.cravedash.restaurant.bootstrap;

import com.cravedash.restaurant.entity.MenuItem;
import com.cravedash.restaurant.entity.Restaurant;
import com.cravedash.restaurant.repository.MenuItemRepository;
import com.cravedash.restaurant.repository.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RestaurantDataLoader implements CommandLineRunner {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    public RestaurantDataLoader(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public void run(String... args) {
        if (restaurantRepository.count() > 0) {
            return;
        }

        Restaurant tandoor = restaurantRepository.save(new Restaurant(null, "Tandoor Grill", "12 MG Road, Bengaluru", "+91 9876543210", new BigDecimal("150.00"), true, 4.7f));
        Restaurant sushi = restaurantRepository.save(new Restaurant(null, "Sushi Nami", "45 Residency Road, Bengaluru", "+91 9123456780", new BigDecimal("200.00"), true, 4.8f));
        Restaurant vegan = restaurantRepository.save(new Restaurant(null, "Green Bowl", "7 Koramangala, Bengaluru", "+91 9988776655", new BigDecimal("120.00"), true, 4.6f));

        menuItemRepository.save(new MenuItem(null, tandoor.getId(), "Butter Chicken", "Classic North Indian curry", new BigDecimal("299.00"), 25, true));
        menuItemRepository.save(new MenuItem(null, tandoor.getId(), "Paneer Tikka", "Smoky grilled cottage cheese", new BigDecimal("249.00"), 20, true));

        menuItemRepository.save(new MenuItem(null, sushi.getId(), "Salmon Roll", "Fresh salmon & avocado roll", new BigDecimal("399.00"), 18, true));
        menuItemRepository.save(new MenuItem(null, sushi.getId(), "Dragon Maki", "Prawn, cucumber, spicy mayo", new BigDecimal("429.00"), 15, true));

        menuItemRepository.save(new MenuItem(null, vegan.getId(), "Quinoa Salad", "Protein-rich mixed greens", new BigDecimal("220.00"), 30, true));
        menuItemRepository.save(new MenuItem(null, vegan.getId(), "Vegan Burger", "Signature plant-based burger", new BigDecimal("260.00"), 22, true));
    }
}
