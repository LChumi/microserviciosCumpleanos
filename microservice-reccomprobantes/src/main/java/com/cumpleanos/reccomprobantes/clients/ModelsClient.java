package com.cumpleanos.reccomprobantes.clients;

import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "models-service")
public interface ModelsClient {

    @GetMapping("/models/empresa/{ruc}")
    ResponseEntity<Sistema> findByRuc(@PathVariable("ruc") String ruc);

    @PostMapping("/models/sri/crear")
    ResponseEntity<SriDocEleEmi> save(@RequestBody SriDocEleEmi sriDocEleEmi);

    @GetMapping("/models/sri-emitido/{claveAcceso}")
    ResponseEntity<SriDocEleEmi> findByClaveAcceso(@PathVariable("claveAcceso") String claveAcceso);

    @PutMapping("/models/sri/actualizado/")
    ResponseEntity<SriDocEleEmi> updateDocument(@RequestBody SriDocEleEmi sriDocEleEmi);
}
