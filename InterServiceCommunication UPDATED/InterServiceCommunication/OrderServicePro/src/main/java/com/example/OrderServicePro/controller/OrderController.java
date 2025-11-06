package com.example.OrderServicePro.controller;

import com.example.OrderServicePro.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody Order dto){

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getOrderWithPayment(@PathVariable Long id)
}
