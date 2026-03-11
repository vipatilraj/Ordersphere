package com.example.ordersphere.entity;

import jakarta.persistence.*;

import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer availableQuantity;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

}