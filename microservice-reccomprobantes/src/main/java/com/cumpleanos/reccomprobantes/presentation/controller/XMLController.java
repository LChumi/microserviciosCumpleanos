package com.cumpleanos.reccomprobantes.presentation.controller;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.service.implementation.XMLConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recp")
@RequiredArgsConstructor
@CrossOrigin("*")
public class XMLController {

    private final XMLConversionService xmlService;

    @PostMapping("/xml")
    public ResponseEntity<Comprobante> getAutorizacionByXML(@RequestBody String xml) {
        Comprobante autorizacion = xmlService.convertirXmlAComprobante(xml, "");
        return ResponseEntity.ok(autorizacion);
    }
}
