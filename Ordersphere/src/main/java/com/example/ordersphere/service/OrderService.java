package com.example.ordersphere.service;

import com.example.ordersphere.DTO.OrderItemRequest;
import com.example.ordersphere.DTO.CreateOrderRequest;
import com.example.ordersphere.entity.*;
import com.example.ordersphere.repository.CustomerRepository;
import com.example.ordersphere.repository.OrderRepository;
import com.example.ordersphere.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    /* Previous code
    @Transactional
    public Order placeOrder(OrderDTO dto){

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(()-> new RuntimeException("Product not found"));

        if(product.getAvailableQuantity() < dto.getQuantity())
        {
            throw new RuntimeException("Not enough quantity");
        }

        product.setAvailableQuantity(product.getAvailableQuantity() - dto.getQuantity());

        Order order = new Order();
        order.setCustomer(customer);
        order.setProduct(product);
        order.setQuantity(dto.getQuantity());
        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);

    }

     */

    @Transactional
    public Order createOrder(CreateOrderRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getAvailableQuantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            product.setAvailableQuantity(
                    product.getAvailableQuantity() - itemRequest.getQuantity()
            );

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);

        return orderRepository.save(order);
    }
}
