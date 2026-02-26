package com.example.ordersphere.service;

import com.example.ordersphere.DTO.ProductDTO;
import com.example.ordersphere.entity.Product;
import com.example.ordersphere.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO dto)
    {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setAvailableQuantity(dto.getAvailableQuantity());

       return productRepository.save(product);

    }
}
