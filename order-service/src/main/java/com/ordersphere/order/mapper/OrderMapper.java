package com.ordersphere.order.mapper;

import com.ordersphere.order.dto.request.OrderRequestDTO;
import com.ordersphere.order.dto.response.OrderLineResponseDTO;
import com.ordersphere.order.dto.response.OrderResponseDTO;
import com.ordersphere.order.entity.*;
import com.ordersphere.order.kafka.event.OrderCreatedEvent;
import com.ordersphere.order.kafka.event.OrderLineEvent;

import java.util.List;

public class OrderMapper {

    //Mapping order dto to order entity
    public static Order toEntity(OrderRequestDTO dto) {
        Order order = Order.builder()
                .customerId(dto.getCustomerId())
                .amount(dto.getAmount())
                .build();

        if (dto.getOrderLines() != null) {
            List<OrderLine> lines = dto.getOrderLines().stream().map(lineDto -> {
                OrderLine line = new OrderLine();
                line.setProductId(lineDto.getProductId());
                line.setQuantity(lineDto.getQuantity());

                line.setOrder(order);
                return line;
            }).toList();

            order.setOrderLines(lines);
        }

        return order;
    }


    //mapping order entity to order dto for response
    public static OrderResponseDTO toDTO(Order order) {
        List<OrderLineResponseDTO> lines = order.getOrderLines().stream()
                .map(line -> OrderLineResponseDTO.builder()
                        .productId(line.getProductId())
                        .quantity(line.getQuantity())
                        .build())
                .toList();

        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .amount(order.getAmount())
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .orderLines(lines)
                .build();
    }


    //Mapping order created message to event
    public static OrderCreatedEvent mapToEvent(Order order) {

        List<OrderLineEvent> lines = order.getOrderLines().stream()
                .map(line -> new OrderLineEvent(
                        line.getProductId(),
                        line.getQuantity()
                ))
                .toList();

        return new OrderCreatedEvent(
                order.getId(),
                order.getCustomerId(),
                order.getAmount(),
                lines
        );
    }
}
