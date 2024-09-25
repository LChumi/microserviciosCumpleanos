package com.cumpleanos.models.controller;

import com.cumpleanos.models.service.ISriDocEleEmiService;
import core.cumpleanos.models.entities.SriDocEleEmi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/sri/actualizado/")
    public ResponseEntity<SriDocEleEmi> updateDocumento(@RequestBody SriDocEleEmi sriDocEleEmi) {
        try {
            SriDocEleEmi existe = sriDocEleEmiService.findById(sriDocEleEmi.getId());
            if (existe != null) {
                existe.setComprobante(sriDocEleEmi.getComprobante());
                existe.setRucEmisor(sriDocEleEmi.getRucEmisor());
                existe.setRazonSocialEmisor(sriDocEleEmi.getRazonSocialEmisor());
                existe.setSerieComprobante(sriDocEleEmi.getSerieComprobante());
                existe.setClaveAcceso(sriDocEleEmi.getClaveAcceso());
                existe.setFechaAutorizacion(sriDocEleEmi.getFechaAutorizacion());
                existe.setFechaEmision(sriDocEleEmi.getFechaEmision());
                existe.setIdentificacionReceptor(sriDocEleEmi.getIdentificacionReceptor());
                SriDocEleEmi update =sriDocEleEmiService.save(existe);
                return ResponseEntity.ok(update);
            }else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
