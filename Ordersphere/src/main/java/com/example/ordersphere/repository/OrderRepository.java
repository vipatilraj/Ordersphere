package com.example.ordersphere.repository;

import com.example.ordersphere.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
