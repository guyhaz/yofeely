package com.example.yofeely.controller;

import com.example.yofeely.dtos.LindoProductsRequest;
import com.example.yofeely.service.LindoIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/integrations")
public class IntegrationController {

    @Autowired
    LindoIntegrationService lindoIntegrationService;

    @PostMapping("/updateLindoStock")
    public ResponseEntity<Object> updateLindoStock() {
        try {
            return ResponseEntity.ok(lindoIntegrationService.updateLindoStock());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/getProductQuantityBySku/{sku}")
    public ResponseEntity<Object> getQuantityBySku(@PathVariable int sku) {
        try {
            return ResponseEntity.ok(lindoIntegrationService.getProductQuantityBySku(sku));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/products/filter")
    public ResponseEntity<Object> filterAllProducts(@RequestBody LindoProductsRequest filter) {
        try {
            return ResponseEntity.ok(lindoIntegrationService.filterProducts(filter));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(e.getMessage());
        }
    }
}
