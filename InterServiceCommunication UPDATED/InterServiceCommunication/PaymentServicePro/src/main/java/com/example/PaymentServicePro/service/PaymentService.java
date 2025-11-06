package com.example.PaymentServicePro.service;

import com.example.PaymentServicePro.entity.Payment;
import com.example.PaymentServicePro.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public void createPayment(Payment payment) {
        paymentRepo.save(payment);
    }
}
