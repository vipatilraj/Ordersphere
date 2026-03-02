package com.example.ordersphere.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Setter
@Getter
public class OrderDTO {

    @NotNull(message = "Please provide customer id")
    private Long customerId;

    @NotEmpty(message = "Please select a item")
    @Valid
    private List<OrderItemDTO> items;

}
