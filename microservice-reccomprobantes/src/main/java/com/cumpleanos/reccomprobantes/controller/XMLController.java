package com.cumpleanos.reccomprobantes.controller;

import com.cumpleanos.reccomprobantes.models.xml.Comprobante;
import com.cumpleanos.reccomprobantes.service.XMLConversionService;
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
        try {
            Comprobante autorizacion = xmlService.convertirXmlAComprobante(xml);
            return ResponseEntity.ok(autorizacion);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
