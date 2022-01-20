package com.yofeely.integration.controller;

import com.yofeely.integration.dtos.Lindo.LindoProductsRequest;
import com.yofeely.integration.service.LindoIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/integrations/lindo")
public class LindoController {

    @Autowired
    LindoIntegrationService lindoIntegrationService;

    @GetMapping("/getProductQuantityBySku/{sku}")
    public ResponseEntity<Object> getQuantityBySku(@PathVariable String sku) {
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
