package com.cravedash.order.service;

import com.cravedash.order.client.PaymentServiceClient;
import com.cravedash.order.client.RestaurantServiceClient;
import com.cravedash.order.dto.CreateOrderRequest;
import com.cravedash.order.dto.OrderDTO;
import com.cravedash.order.entity.Order;
import com.cravedash.order.entity.OrderItem;
import com.cravedash.order.repository.OrderItemRepository;
import com.cravedash.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private RestaurantServiceClient restaurantServiceClient;

    @Autowired
    private PaymentServiceClient paymentServiceClient;
    
    public OrderDTO createOrder(CreateOrderRequest request) {
        if (request == null || request.getItems() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        try {
            restaurantServiceClient.getRestaurant(request.getRestaurantId());
        } catch (Exception e) {
            throw new RuntimeException("Restaurant not found");
        }
        
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (var itemRequest : request.getItems()) {
            totalAmount = totalAmount.add(itemRequest.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
        }

        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setRestaurantId(request.getRestaurantId());
        order.setOrderStatus("PLACED");
        order.setTotalAmount(totalAmount);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        
        Order savedOrder = orderRepository.save(order);
        
        for (var itemRequest : request.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(savedOrder.getId());
            orderItem.setMenuItemId(itemRequest.getMenuItemId());
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(itemRequest.getPrice());
            
            orderItemRepository.save(orderItem);
        }

        Map<String, Object> paymentRequest = new HashMap<>();
        paymentRequest.put("orderId", savedOrder.getId());
        paymentRequest.put("customerId", savedOrder.getCustomerId());
        paymentRequest.put("amount", totalAmount);
        paymentRequest.put("paymentMethod", "CARD");

        try {
            paymentServiceClient.processPayment(paymentRequest);
            savedOrder.setOrderStatus("PAYMENT_CONFIRMED");
        } catch (Exception e) {
            savedOrder.setOrderStatus("PAYMENT_FAILED");
        }

        Order finalOrder = orderRepository.save(savedOrder);
        return convertToDTO(finalOrder);
    }
    
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDTO(order);
    }
    
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        Order updated = orderRepository.save(order);
        return convertToDTO(updated);
    }
    
    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getCustomerId(),
                order.getRestaurantId(),
                order.getOrderStatus(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getDeliveryPersonId()
        );
    }
}
