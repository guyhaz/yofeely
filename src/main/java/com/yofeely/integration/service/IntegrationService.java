package com.yofeely.integration.service;

import com.yofeely.integration.dtos.Lindo.LindoProductsRequest;
import com.yofeely.integration.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IntegrationService {

    @Autowired
    MagentoIntegrationService magentoIntegrationService;

    @Autowired
    LindoIntegrationService lindoIntegrationService;

    public List<String> updateLindoStock() {
        List<String> results = new ArrayList<>();
        try {
            List<Product> products = lindoIntegrationService.filterProducts(LindoProductsRequest.builder()
//                    .sku("652638004655")
                    .departmentId(102)
                    .showOutOfStockProducts(0)
                    .build());
            String message;
            int count = 0;
            for (Product product : products) {
                try {
                    if (product.getQuantity().equals("false")) {
                        magentoIntegrationService.updateStockQtyBySku(product.getSku(), 0);
                    } else {
                        magentoIntegrationService.updateStockQtyBySku(product.getSku(), Integer.parseInt(product.getQuantity()));
                    }
                    message = "Sku: " + product.getSku() + " updated successfully";
                    System.out.println("Processed so far: " + count++ + ". " + message);
                    results.add(message);
                } catch (Exception e) {
                    e.printStackTrace();
                    results.add("Sku: " + product.getSku() + " failed to update");
                }
            }
            System.out.println(results.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
