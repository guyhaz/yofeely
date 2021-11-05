package com.yofeely.integration.dtos.Lindo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Value
@Builder
@JsonInclude(NON_NULL)
public class LindoProductsRequest {

    @JsonProperty("barcode")
    Integer sku;

    @JsonProperty("department_id")
    Integer departmentId;

    @JsonProperty("stock_availability")
    Integer showOutOfStockProducts;
}
