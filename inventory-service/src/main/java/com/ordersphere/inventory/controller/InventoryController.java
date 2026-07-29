package com.ordersphere.inventory.controller;

import com.ordersphere.inventory.dto.Request.InventoryManipulateDTO;
import com.ordersphere.inventory.dto.Request.InventoryRequestDTO;
import com.ordersphere.inventory.dto.Response.InventoryResponseDTO;
import com.ordersphere.inventory.repository.InventoryRepository;
import com.ordersphere.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    //private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;

//Get product stock byId
    @GetMapping("/{productId}")
    public InventoryResponseDTO getInventory(@PathVariable Long productId) {
        return inventoryService.getInventory(productId);
    }

//Add the stocks to inventory
    @PostMapping("/add")
    public String addStock(@RequestBody InventoryManipulateDTO dto) {
        inventoryService.addStock(dto.getProductId(), dto.getStock());
        return "Stock added";
    }

//Reduce the stocks from inventory
    @PostMapping("/reduce")
    public String reduceStock(@RequestBody InventoryManipulateDTO dto) {

        inventoryService.reduceStock(dto.getProductId(), dto.getStock());
        return "Stock reduced";
    }

//Adding a new product to inventory
    @PostMapping("/addProduct")
    public InventoryResponseDTO addProduct(@RequestBody InventoryRequestDTO dto) {

        return inventoryService.addProduct(dto);
    }
}
