package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingLineMetaDatum {
    private long id;
    private String key;
    private String value;
    private String display_key;
    private String display_value;
}
