package com.cumpleanos.models.controller;

import com.cumpleanos.models.service.ISriDocEleEmiService;
import core.cumpleanos.models.entities.SriDocEleEmi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@Slf4j
public class SriDocEleEmiServiceController {

    @Autowired
    private ISriDocEleEmiService sriDocEleEmiService;

    @GetMapping("/sri-emitido/{claveAcceso}")
    public ResponseEntity<SriDocEleEmi> getDocumento(@PathVariable String claveAcceso) {
        try{
            SriDocEleEmi doc = sriDocEleEmiService.findByClaveAcceso(claveAcceso);
            return doc != null ? ResponseEntity.ok(doc) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
