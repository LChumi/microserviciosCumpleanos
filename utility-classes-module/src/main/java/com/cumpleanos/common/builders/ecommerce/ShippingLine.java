package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShippingLine {
    private long id;
    private String method_title;
    private String method_id;
    private String instance_id;
    private String total;
    private String total_tax;
    private List<Object> taxes;
    private List<ShippingLineMetaDatum> meta_data;
}
