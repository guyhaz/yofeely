package com.yofeely.integration.controller;

import com.yofeely.integration.dtos.magento.MagentoProductResponse;
import com.yofeely.integration.dtos.magento.StockItem;
import com.yofeely.integration.service.MagentoIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/integrations/magento")
public class MagentoController {

    @Autowired
    MagentoIntegrationService magentoIntegrationService;

    @GetMapping("/getProductBySku/{sku}")
    public MagentoProductResponse getProductBySku(@PathVariable String sku) {
        return magentoIntegrationService.getProductBySku(sku);
    }

    @PostMapping("/updateStockBySku/{sku}")
    public String updateStockBySku(@PathVariable String sku, @RequestBody Integer quantity) {
        try {
            return magentoIntegrationService.updateStockQtyBySku(sku, quantity);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
