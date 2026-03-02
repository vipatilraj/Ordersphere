package com.example.ordersphere.service;


import com.example.ordersphere.DTO.*;
import com.example.ordersphere.entity.*;
import com.example.ordersphere.exception.*;
import com.example.ordersphere.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_success() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(1L);

        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setProductId(1L);
        itemDTO.setQuantity(2);

        orderDTO.setItems(List.of(itemDTO));

        Customer customer = new Customer();
        customer.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);
        product.setAvailableQuantity(10);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));


        OrderResponseDTO response = orderService.createOrder(orderDTO);

        assertNotNull(response);
        assertEquals(2000.0, response.getTotalAmount());
        assertEquals(1, response.getItems().size());
    }

    @Test
    void createOrder_customerNotFound() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(99L);
        orderDTO.setItems(new ArrayList<>());

        when(customerRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> orderService.createOrder(orderDTO));
    }

    @Test
    void createOrder_insufficientStock() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerId(1L);

        OrderItemDTO itemDTO = new OrderItemDTO();
        itemDTO.setProductId(1L);
        itemDTO.setQuantity(20);

        orderDTO.setItems(List.of(itemDTO));

        Customer customer = new Customer();
        customer.setId(1L);

        Product product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(1000.0);
        product.setAvailableQuantity(5);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(InsufficientStockException.class,
                () -> orderService.createOrder(orderDTO));
    }
}
