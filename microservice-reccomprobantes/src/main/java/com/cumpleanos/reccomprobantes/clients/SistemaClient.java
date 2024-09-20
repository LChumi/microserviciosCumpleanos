package com.cumpleanos.reccomprobantes.clients;

import core.cumpleanos.models.entities.Sistema;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "models-service")
public interface SistemaClient {

    @GetMapping("/models/empresa/{ruc}")
    ResponseEntity<Sistema> findByRuc(@PathVariable("ruc") String ruc);
}
