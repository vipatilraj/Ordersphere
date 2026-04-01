package com.ordersphere.order.kafka.event;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private Double amount;
}