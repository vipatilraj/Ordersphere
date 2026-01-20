package com.example.ordersphere.controller;


import com.example.ordersphere.DTO.ProductDTO;
import com.example.ordersphere.entity.Product;
import com.example.ordersphere.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDTO dto)
    {
        return productService.createProduct(dto);
    }

}
