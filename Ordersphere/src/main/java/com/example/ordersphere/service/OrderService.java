package com.example.ordersphere.service;

import com.example.ordersphere.DTO.OrderDTO;
import com.example.ordersphere.entity.Customer;
import com.example.ordersphere.entity.Order;
import com.example.ordersphere.entity.OrderStatus;
import com.example.ordersphere.entity.Product;
import com.example.ordersphere.repository.CustomerRepository;
import com.example.ordersphere.repository.OrderRepository;
import com.example.ordersphere.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}
