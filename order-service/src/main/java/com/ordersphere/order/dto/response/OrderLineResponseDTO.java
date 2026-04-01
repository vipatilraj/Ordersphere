package com.ordersphere.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderLineResponseDTO {
    private Long productId;
    private int quantity;
}
