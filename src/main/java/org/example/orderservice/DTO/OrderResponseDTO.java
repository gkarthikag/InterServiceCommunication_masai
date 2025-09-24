package org.example.orderservice.DTO;

public class OrderResponseDTO {
    private Long id;
    private String productName;
    private Integer quantity;
    private String paymentStatus;

    // Constructors
    public OrderResponseDTO() {}

    public OrderResponseDTO(Long id, String productName, Integer quantity, String paymentStatus) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
