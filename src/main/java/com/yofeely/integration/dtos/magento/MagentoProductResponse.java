package com.yofeely.integration.dtos.magento;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonDeserialize
public class MagentoProductResponse {
    String id;

    String sku;

    String name;
}
