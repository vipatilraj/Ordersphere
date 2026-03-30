package com.ordersphere.order.mapper;

import com.ordersphere.order.dto.request.OrderRequestDTO;
import com.ordersphere.order.dto.response.OrderResponseDTO;
import com.ordersphere.order.entity.*;

public class OrderMapper {
    public static Order toEntity(OrderRequestDTO dto) {
        return Order.builder()
                .customerId(dto.getCustomerId())
                .amount(dto.getAmount())
                .build();
    }

    public static OrderResponseDTO toDTO(Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .amount(order.getAmount())
                .status(order.getStatus().name())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
