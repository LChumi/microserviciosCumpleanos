package com.cumpleanos.pos.service.http;

import com.cumpleanos.core.models.entities.Sistema;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "models-service")
public interface IModelsClient {

    @GetMapping("/models/id-empresa/{id}")
    ResponseEntity<Sistema> getEmpresaById(@PathVariable Long id);
}
