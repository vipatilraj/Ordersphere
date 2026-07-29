package com.ordersphere.inventory.mapper;

import com.ordersphere.inventory.dto.Request.InventoryRequestDTO;
import com.ordersphere.inventory.dto.Response.InventoryResponseDTO;
import com.ordersphere.inventory.entity.Inventory;


public class InventoryMapper {

//Mapping inventory dto to inventory entity
    public static Inventory toEntity(InventoryRequestDTO dto) {
        return Inventory.builder()
                .productId(dto.getProductId())
                .productName(dto.getProductName())
                .stock(dto.getStock())
                .build();
    }

//Mapping inventory entity to response dto
    public static InventoryResponseDTO toResponseDTO(Inventory inventory)
    {
        return InventoryResponseDTO.builder()
                .productId(inventory.getProductId())
                .productName(inventory.getProductName())
                .stock(inventory.getStock())
                .build();
    }
}
