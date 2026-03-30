package com.ordersphere.order.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private Double amount;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
