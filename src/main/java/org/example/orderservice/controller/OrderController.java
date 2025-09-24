package org.example.orderservice.controller;

import jakarta.validation.Valid;
import org.example.orderservice.DTO.OrderRequestDTO;
import org.example.orderservice.DTO.OrderResponseDTO;
import org.example.orderservice.exception.OrderNotFoundException;
import org.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {
        OrderResponseDTO response = orderService.createOrder(orderRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable Long id,
                                                     @RequestParam(defaultValue = "resttemplate") String type) {
        try {
            OrderResponseDTO response = orderService.getOrderWithPaymentStatus(id, type);
            return ResponseEntity.ok(response);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Separate endpoints for demonstration
    @GetMapping("/{id}/resttemplate")
    public ResponseEntity<OrderResponseDTO> getOrderUsingRestTemplate(@PathVariable Long id) {
        try {
            OrderResponseDTO response = orderService.getOrderWithPaymentStatus(id, "resttemplate");
            return ResponseEntity.ok(response);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/webclient")
    public ResponseEntity<OrderResponseDTO> getOrderUsingWebClient(@PathVariable Long id) {
        try {
            OrderResponseDTO response = orderService.getOrderWithPaymentStatus(id, "webclient");
            return ResponseEntity.ok(response);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/feignclient")
    public ResponseEntity<OrderResponseDTO> getOrderUsingFeignClient(@PathVariable Long id) {
        try {
            OrderResponseDTO response = orderService.getOrderWithPaymentStatus(id, "feignclient");
            return ResponseEntity.ok(response);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
