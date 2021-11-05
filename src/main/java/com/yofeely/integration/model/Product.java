package com.yofeely.integration.model;

import com.yofeely.integration.dtos.Lindo.LindoProductsResponse;
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
