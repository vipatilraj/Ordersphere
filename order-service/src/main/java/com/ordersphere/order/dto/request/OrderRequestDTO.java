package com.ordersphere.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequestDTO {
    @NotNull
    private Long customerId;

    @NotNull
    private Double amount;

}
