package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidosWoocommerceMetaDatum {
    private long id;
    private String key;
    private String value;
}
