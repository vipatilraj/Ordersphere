package com.example.ordersphere.controller;

import com.example.ordersphere.DTO.OrderDTO;
import com.example.ordersphere.DTO.OrderResponseDTO;
import com.example.ordersphere.entity.Order;
import com.example.ordersphere.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

}
