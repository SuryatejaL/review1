package com.cravedash.order.controller;

import com.cravedash.order.dto.CreateOrderRequest;
import com.cravedash.order.dto.OrderDTO;
import com.cravedash.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest request) {
        OrderDTO order = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getCustomerOrders(@PathVariable Long customerId) {
        List<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        OrderDTO order = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(order);
    }
}
