package com.cumpleanos.scheduler.service.http;

import com.cumpleanos.core.models.dto.ProductEcomRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@FeignClient(name = "ecommerce-service")
public interface IEcommerceClient {

    @GetMapping("/ecommerce/woocommerce/orders-date/{d1}/{d2}")
    ResponseEntity<List<Map<String, Object>>> getOrdersDate(@PathVariable LocalDate d1, @PathVariable LocalDate d2);

    @PutMapping("/ecommerce/woocommerce/products/{sku}")
    ResponseEntity<Map<String,Object>> updateProduct(@PathVariable String sku, ProductEcomRequest request);
}