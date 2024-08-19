package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.models.service.interfaces.ISriDocEleEmiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Tag(name = "SriDoc", description = "Documentacion API de Docuemntos SRI recibidos")
public class SriDocEleEmiServiceController {

    private final ISriDocEleEmiService sriDocEleEmiService;

    @Operation(summary = "Obtener Documento", description = "Obtiene el documento emitido por clave de acceso", tags = {"SriDoc"}, responses = {
            @ApiResponse(responseCode = "200", description = "Documento emitido")
    })
    @Parameter(name = "claveAcceso", description = "Clave de accesos del documento")
    @GetMapping("/sri-doc/{claveAcceso}")
    public ResponseEntity<SriDocEleEmi> getDocumento(@PathVariable String claveAcceso) {
        SriDocEleEmi existingDoc  = sriDocEleEmiService.findByClaveAcceso(claveAcceso);
        if (existingDoc == null) {
            log.warn("No se encontro el documento");
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(existingDoc);
    }

    @Operation(summary = "Crear Documento", description = "Crea un nuevo documento recibido", tags = {"SriDoc"}, responses = {
            @ApiResponse(responseCode = "200", description = "Documento Creado")
    })
    @PostMapping("/sri-doc/crear")
    public ResponseEntity<SriDocEleEmi> getDocumento(@RequestBody SriDocEleEmi sriDocEleEmi) {
        SriDocEleEmi existingDoc  = sriDocEleEmiService.findById(sriDocEleEmi.getId());
        if (existingDoc  != null) {
            return ResponseEntity.ok(existingDoc );
        } else {
            SriDocEleEmi nuevoDoc = sriDocEleEmiService.save(sriDocEleEmi);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDoc);
        }
    }

    @Operation(summary = "Actualizar Documento", description = "Actualiza el documento en el sistema", tags = {"SriDoc"}, responses = {
            @ApiResponse(responseCode = "200", description = "Documento Actualizado")
    })
    @PutMapping("/sri-doc/actualizado/")
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
