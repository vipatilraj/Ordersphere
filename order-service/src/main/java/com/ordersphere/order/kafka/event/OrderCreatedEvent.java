package com.ordersphere.order.kafka.event;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private Double amount;
    private List<OrderLineEvent> orderLines;
}