package com.cumpleanos.scheduler.service.http;

import com.cumpleanos.scheduler.models.ServiceResponse;
import com.cumpleanos.scheduler.models.StockEcommerceVDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "assist-service")
public interface AssistClient {

    @GetMapping("/assist/ecommerce/stock/view")
    ResponseEntity<List<StockEcommerceVDTO>> findAll();

    @GetMapping("/assist/ecommerce/product-update/{id}/{empresa}/{sku}/{process}")
    ResponseEntity<ServiceResponse> getProductoUpdate(@PathVariable Long id, @PathVariable Long empresa, @PathVariable String sku, @PathVariable Integer process);
}
