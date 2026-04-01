package com.ordersphere.order.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class OrderLineEvent {
    private Long productId;
    private int quantity;

}
