package com.example.ordersphere.DTO;

import lombok.*;

import java.util.List;

@Setter
@Getter
public class OrderDTO {

    private Long customerId;
    private List<OrderItemDTO> items;


    /* Previous code
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

     */
}
