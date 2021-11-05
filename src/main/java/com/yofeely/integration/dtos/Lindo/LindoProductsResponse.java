package com.yofeely.integration.dtos.Lindo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@JsonDeserialize
public class LindoProductsResponse {

    String barcode;

    String product_quantity;

    String english_product_name;
}
