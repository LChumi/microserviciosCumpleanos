package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.SolicitudCompraImportacionDTO;
import com.cumpleanos.models.service.interfaces.ISolicitudCompraImportacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SolicitudCompraImportacionController {

    private final ISolicitudCompraImportacionService solicitudCompraService;

    @GetMapping("ver-sci/{cco}")
    public ResponseEntity<SolicitudCompraImportacionDTO> verSolicitudCompro(@PathVariable("cco") BigInteger cco) {
        SolicitudCompraImportacionDTO response = solicitudCompraService.getSolicitudComproImportacion(cco);
        return ResponseEntity.ok(response);
    }
}
