package com.example.PaymentServicePro.controller;

import com.example.PaymentServicePro.entity.Payment;
import com.example.PaymentServicePro.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<?> createPayment(@RequestBody Payment payment){
        paymentService.createPayment(payment);
        return new ResponseEntity<>("Payment Created Successfully", HttpStatus.OK);
    }

    @GetMapping("/payments/{orderId}")
    public ResponseEntity<?> getPaymentStatus(@PathVariable Long orderId){

    }
}
