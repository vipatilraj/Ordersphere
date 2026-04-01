package com.ordersphere.order.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    @NotNull
    private Long customerId;

    @NotNull
    private Double amount;

    @NotNull
    private List<OrderLineRequest> orderLines;
}
