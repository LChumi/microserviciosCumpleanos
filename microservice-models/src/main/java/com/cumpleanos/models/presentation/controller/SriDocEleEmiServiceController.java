package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.models.service.interfaces.ISriDocEleEmiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        SriDocEleEmi existingDoc  = sriDocEleEmiService.findByClaveAcceso(claveAcceso);
        if (existingDoc == null) {
            log.warn("No se encontro el documento");
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(existingDoc);
    }

    @PostMapping("/sri/crear")
    public ResponseEntity<SriDocEleEmi> getDocumento(@RequestBody SriDocEleEmi sriDocEleEmi) {
        SriDocEleEmi existingDoc  = sriDocEleEmiService.findById(sriDocEleEmi.getId());
        if (existingDoc  != null) {
            return ResponseEntity.ok(existingDoc );
        } else {
            SriDocEleEmi nuevoDoc = sriDocEleEmiService.save(sriDocEleEmi);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDoc);
        }
    }

    @PutMapping("/sri/actualizado/")
    public ResponseEntity<SriDocEleEmi> updateDocumento(@RequestBody SriDocEleEmi sriDocEleEmi) {
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
    }

}
