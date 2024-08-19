package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tax {
    private long id;
    private String total;
    private String subtotal;
}
