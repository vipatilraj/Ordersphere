package com.example.ordersphere.controller;

import com.example.ordersphere.DTO.OrderDTO;
import com.example.ordersphere.entity.Order;
import com.example.ordersphere.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody OrderDTO dto)
    {
        return orderService.placeOrder(dto);
    }


}
