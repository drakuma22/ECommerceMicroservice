package com.microservice.inventory_service.controller;

import com.microservice.inventory_service.dto.InventoryRequest;
import com.microservice.inventory_service.model.Inventory;
import com.microservice.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory inventoryResponse(@RequestBody InventoryRequest inventoryRequest){
        return inventoryService.addInventory(inventoryRequest);
    }
}
