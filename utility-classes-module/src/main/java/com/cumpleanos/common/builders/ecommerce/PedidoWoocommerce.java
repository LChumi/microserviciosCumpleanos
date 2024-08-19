package com.cumpleanos.common.builders.ecommerce;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PedidoWoocommerce {
    private long id;
    private long parent_id;
    private String status;
    private String currency;
    private String version;
    private boolean prices_include_tax;
    private String date_created;
    private String date_modified;
    private String discount_total;
    private String discount_tax;
    private String shipping_total;
    private String shipping_tax;
    private String cart_tax;
    private String total;
    private String total_tax;
    private long customer_id;
    private String order_key;
    private Ing billing;
    private Ing shipping;
    private String payment_method;
    private String payment_method_title;
    private String transaction_id;
    private String customer_ip_address;
    private String customer_user_agent;
    private String created_via;
    private String customer_note;
    private Object date_completed;
    private Object date_paid;
    private String cart_hash;
    private String number;
    private PedidosWoocommerceMetaDatum[] meta_data;
    private List<LineItem> line_items;
    private List<TaxLine> tax_lines;
    private List<ShippingLine> shipping_lines;
    private List<Object> fee_lines;
    private List<Object> coupon_lines;
    private List<Object> refunds;
    private String payment_url;
    private boolean is_editable;
    private boolean needs_payment;
    private boolean needs_processing;
    private String date_created_gmt;
    private String date_modified_gmt;
    private Object date_completed_gmt;
    private Object date_paid_gmt;
    private String currency_symbol;
}
