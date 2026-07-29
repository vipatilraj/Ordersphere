package com.ordersphere.inventory.service;

import com.ordersphere.inventory.dto.Request.InventoryRequestDTO;
import com.ordersphere.inventory.dto.Response.InventoryResponseDTO;

public interface InventoryService {
    void reduceStock(Long productId, int quantity);

    void addStock(Long productId, int quantity);

    InventoryResponseDTO getInventory(Long productId);

    InventoryResponseDTO addProduct(InventoryRequestDTO dto);
}