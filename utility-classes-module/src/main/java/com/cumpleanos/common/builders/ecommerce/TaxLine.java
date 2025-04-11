package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaxLine {
    private long id;
    private String rate_code;
    private long rate_id;
    private String label;
    private boolean compound;
    private String tax_total;
    private String shipping_tax_total;
    private long rate_percent;
    private List<Object> meta_data;
}
