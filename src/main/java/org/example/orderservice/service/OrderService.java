package org.example.orderservice.service;

import org.example.orderservice.DTO.OrderRequestDTO;
import org.example.orderservice.DTO.OrderResponseDTO;
import org.example.orderservice.entity.Order;
import org.example.orderservice.exception.OrderNotFoundException;
import org.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentCommunicationService paymentCommunicationService;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        Order order = new Order(orderRequest.getProductName(), orderRequest.getQuantity());
        Order savedOrder = orderRepository.save(order);
        return mapToResponseDTO(savedOrder, null);
    }

    public OrderResponseDTO getOrderWithPaymentStatus(Long orderId, String communicationType) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            String paymentStatus = getPaymentStatus(orderId, communicationType);
            return mapToResponseDTO(order, paymentStatus);
        } else {
            throw new OrderNotFoundException("Order not found with ID: " + orderId);
        }
    }

    private String getPaymentStatus(Long orderId, String communicationType) {
        switch (communicationType.toLowerCase()) {
            case "resttemplate":
                return paymentCommunicationService.getPaymentStatusUsingRestTemplate(orderId);
            case "webclient":
                return paymentCommunicationService.getPaymentStatusUsingWebClient(orderId);
            case "feignclient":
                return paymentCommunicationService.getPaymentStatusUsingFeignClient(orderId);
            default:
                return paymentCommunicationService.getPaymentStatusUsingRestTemplate(orderId);
        }
    }

    private OrderResponseDTO mapToResponseDTO(Order order, String paymentStatus) {
        return new OrderResponseDTO(order.getId(), order.getProductName(), order.getQuantity(), paymentStatus);
    }
}
