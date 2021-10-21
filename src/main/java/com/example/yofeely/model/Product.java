package com.example.yofeely.model;

import com.example.yofeely.dtos.LindoProductsResponse;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Product {
    String sku;
    String quantity;

    public static Product from(LindoProductsResponse lindoProductsResponse) {
        return Product.builder()
                .sku(lindoProductsResponse.getBarcode())
                .quantity(lindoProductsResponse.getProduct_quantity())
                .build();
    }
}
