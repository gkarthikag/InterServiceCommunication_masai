package org.example.orderservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderRequestDTO {
    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    // Constructors
    public OrderRequestDTO() {}

    public OrderRequestDTO(String productName, Integer quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    // Getters and Setters
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
}
