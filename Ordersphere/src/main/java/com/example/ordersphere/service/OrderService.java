package com.example.ordersphere.service;

import com.example.ordersphere.DTO.OrderDTO;
import com.example.ordersphere.DTO.OrderItemDTO;
import com.example.ordersphere.DTO.OrderItemResponseDTO;
import com.example.ordersphere.DTO.OrderResponseDTO;
import com.example.ordersphere.entity.*;
import com.example.ordersphere.exception.InsufficientStockException;
import com.example.ordersphere.exception.ResourceNotFoundException;
import com.example.ordersphere.repository.*;
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

    @Transactional
    public OrderResponseDTO createOrder(OrderDTO orderDTO) {
        //Checking valid customer
        Customer customer = customerRepository.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);

        List<OrderItem> orderItems = new ArrayList<>();

        double totalAmount = 0.0;
        for (OrderItemDTO itemDTO : orderDTO.getItems()) {

            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product not found"));

            if (product.getAvailableQuantity() < itemDTO.getQuantity()) {
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
            }

            product.setAvailableQuantity(
                    product.getAvailableQuantity() - itemDTO.getQuantity()
            );

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.getQuantity());
            double lineTotal = product.getPrice() * itemDTO.getQuantity();
            totalAmount += lineTotal;
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(totalAmount);
        
        //Saving the order
        Order savedOrder = orderRepository.save(order);

        return getOrderDetails(savedOrder);
    }


//putting details in response dto
 public OrderResponseDTO getOrderDetails(Order order)
 {
     OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
     orderResponseDTO.setOrderId(order.getId());
     orderResponseDTO.setCustomerId(order.getCustomer().getId());
     orderResponseDTO.setOrderDate(order.getOrderDate());

     List<OrderItemResponseDTO> itemResponses = new ArrayList<>();

     for (OrderItem item : order.getOrderItems()) {
         OrderItemResponseDTO itemDTO = new OrderItemResponseDTO();
         itemDTO.setProductId(item.getProduct().getId());
         itemDTO.setProductName(item.getProduct().getName());
         itemDTO.setQuantity(item.getQuantity());
         itemResponses.add(itemDTO);
     }

     orderResponseDTO.setItems(itemResponses);
     orderResponseDTO.setTotalAmount(order.getTotalAmount());
     return orderResponseDTO;
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

}
