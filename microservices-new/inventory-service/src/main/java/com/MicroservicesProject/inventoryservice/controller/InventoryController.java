package com.MicroservicesProject.inventoryservice.controller;

import com.MicroservicesProject.inventoryservice.dto.InventoryResponse;
import com.MicroservicesProject.inventoryservice.repository.InventoryRepository;
import com.MicroservicesProject.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    //http://locahost:8082/api/inventory/iphone-13,iphone13-red   ///PathVariable
    //http://locahost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red  ///RequestParam

//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean isInStock(@PathVariable("sku-code") String skuCode){
//        return inventoryService.isInStock(skuCode);
//    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }

}
