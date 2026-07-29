package com.ordersphere.inventory.kafka.consumer;

import com.ordersphere.inventory.kafka.event.OrderCreatedEvent;
import com.ordersphere.inventory.kafka.event.OrderLineEvent;
import com.ordersphere.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InventoryConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(topics = "order-created", groupId = "inventory-group")
    public void consume(OrderCreatedEvent event) {

        for (OrderLineEvent line : event.getOrderLines()) {

            inventoryService.reduceStock(
                    line.getProductId(),
                    line.getQuantity()
            );

            System.out.println("Stock reduced for product: " + line.getProductId());
        }
    }
}