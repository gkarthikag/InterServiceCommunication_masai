package org.example.orderservice.FeignClient;

import org.example.orderservice.DTO.PaymentResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-service", url = "${payment.service.url}")
public interface PaymentServiceClient {

    @GetMapping("/payments/{orderId}")
    PaymentResponseDTO getPaymentByOrderId(@PathVariable("orderId") Long orderId);
}
