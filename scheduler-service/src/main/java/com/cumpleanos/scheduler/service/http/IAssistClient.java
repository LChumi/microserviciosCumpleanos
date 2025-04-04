package com.cumpleanos.scheduler.service.http;

import com.cumpleanos.core.models.dto.ServiceResponse;
import com.cumpleanos.core.models.views.StockEcommerceV;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "assist-service")
public interface IAssistClient {

    @GetMapping("/assist/ecommerce/producto-save/{id}/{empresa}")
    ResponseEntity<ServiceResponse> getProducto(@PathVariable Long id, @PathVariable Long empresa);

    @GetMapping("/assist/ecommerce/stock/view")
    ResponseEntity<List<StockEcommerceV>> findAll();
}
