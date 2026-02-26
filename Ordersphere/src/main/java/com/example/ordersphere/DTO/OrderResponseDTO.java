package com.example.ordersphere.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderResponseDTO {

    private Long orderId;
    private Long customerId;
    private LocalDate orderDate;
    private List<OrderItemResponseDTO> items;
    double totalAmount;

}
