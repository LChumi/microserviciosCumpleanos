package com.cumpleanos.reccomprobantes.clients;

import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "models-service")
public interface ModelsClient {

    @GetMapping("/models/empresa/{ruc}")
    ResponseEntity<Sistema> findByRuc(@PathVariable("ruc") String ruc);

    @GetMapping("/models/sri-emitido/{claveAcceso}")
    ResponseEntity<SriDocEleEmi> findByClaveAcceso(@PathVariable("claveAcceso") String claveAcceso);

    @PutMapping("/models/sri/actualizado/")
    ResponseEntity<SriDocEleEmi> updateDocument(@RequestBody SriDocEleEmi sriDocEleEmi);
}
