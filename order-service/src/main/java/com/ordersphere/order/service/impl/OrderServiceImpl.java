package com.ordersphere.order.service.impl;

import com.ordersphere.order.dto.request.OrderRequestDTO;
import com.ordersphere.order.dto.response.OrderResponseDTO;
import com.ordersphere.order.entity.Order;
import com.ordersphere.order.mapper.OrderMapper;
import com.ordersphere.order.repository.OrderRepository;
import com.ordersphere.order.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO request) {

        Order order = OrderMapper.toEntity(request);

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.toDTO(savedOrder);
    }

    @Transactional
    @Override
    public OrderResponseDTO getOrder(Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return OrderMapper.toDTO(order);
    }
}