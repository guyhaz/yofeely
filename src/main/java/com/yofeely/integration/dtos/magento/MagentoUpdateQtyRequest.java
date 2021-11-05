package com.yofeely.integration.dtos.magento;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MagentoUpdateQtyRequest {
    StockItem stockItem;
}
