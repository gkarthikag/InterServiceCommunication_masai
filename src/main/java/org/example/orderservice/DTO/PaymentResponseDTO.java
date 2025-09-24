package org.example.orderservice.DTO;

public class PaymentResponseDTO {
    private Long id;
    private Long orderId;
    private String status;

    // Constructors
    public PaymentResponseDTO() {}

    public PaymentResponseDTO(Long id, Long orderId, String status) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}