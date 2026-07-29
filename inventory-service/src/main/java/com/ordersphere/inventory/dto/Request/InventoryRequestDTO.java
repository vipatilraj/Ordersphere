package com.ordersphere.inventory.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequestDTO {
    private Long productId;
    private String productName;
    private int stock;
}