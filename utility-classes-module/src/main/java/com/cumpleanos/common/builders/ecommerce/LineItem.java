package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class LineItem {
    private long id;
    private String name;
    private long product_id;
    private long variation_id;
    private long quantity;
    private String tax_class;
    private String subtotal;
    private String subtotal_tax;
    private double total;
    private String total_tax;
    private List<Tax> taxes;
    private List<Map<String, Object>> meta_data;
    private String sku;
    private double price;
    private Image image;
    private Object parent_name;

}
