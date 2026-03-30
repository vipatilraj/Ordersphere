package com.example.ordersphere.repository;

import com.example.ordersphere.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
