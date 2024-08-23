package com.microservice.inventory_service.service;

import com.microservice.inventory_service.dto.InventoryRequest;
import com.microservice.inventory_service.dto.InventoryResponse;
import com.microservice.inventory_service.model.Inventory;
import com.microservice.inventory_service.repository.InventoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean isInStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

    public Inventory addInventory(InventoryRequest inventoryRequest){
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
        return inventoryRepository.save(inventory);
    }
}
