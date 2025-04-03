package com.cumpleanos.scheduler.service.http;

import com.cumpleanos.core.models.dto.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "assist-service")
public interface IAssistClient {

    @GetMapping("/ecommerce/producto-save/{id}/{empresa}")
    ResponseEntity<ServiceResponse> getProducto(@PathVariable Long id, @PathVariable Long empresa);
}
