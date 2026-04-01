package com.ordersphere.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderLineRequest {

    @NotNull
    private Long productId;

    @NotNull
    private int quantity;
}
