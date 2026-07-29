package com.ordersphere.inventory.service.impl;


import com.ordersphere.inventory.dto.Request.InventoryRequestDTO;
import com.ordersphere.inventory.dto.Response.InventoryResponseDTO;
import com.ordersphere.inventory.entity.Inventory;
import com.ordersphere.inventory.mapper.InventoryMapper;
import com.ordersphere.inventory.repository.InventoryRepository;
import com.ordersphere.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

// Reducing the stocks
    @Override
    public void reduceStock(Long productId, int quantity) {

        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (inventory.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        inventory.setStock(inventory.getStock() - quantity);
        inventoryRepository.save(inventory);
    }

//Adding the stock
    @Override
    public void addStock(Long productId, int quantity) {

        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        inventory.setStock(inventory.getStock() + quantity);
        inventoryRepository.save(inventory);
    }


//Get inventory
    @Override
    public InventoryResponseDTO getInventory(Long productId) {
        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        InventoryResponseDTO inventoryResponseDTO = new InventoryResponseDTO();
        inventoryResponseDTO.setProductId(inventory.getProductId());
        inventoryResponseDTO.setProductName(inventory.getProductName());
        inventoryResponseDTO.setStock(inventory.getStock());

        return inventoryResponseDTO;
    }

//Adding new product
    @Override
    public InventoryResponseDTO addProduct(InventoryRequestDTO dto)
    {
        Inventory inventory = InventoryMapper.toEntity(dto);
        inventoryRepository.save(inventory);

        return InventoryMapper.toResponseDTO(inventory);
    }


}