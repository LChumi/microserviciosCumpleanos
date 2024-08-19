package com.cumpleanos.reccomprobantes.controller;

import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.service.XMLConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recp")
@RequiredArgsConstructor
@CrossOrigin("*")
public class XMLController {

    private final XMLConversionService xmlService;

    @PostMapping("/factura")
    public ResponseEntity<Factura> getFacturaByXML(@RequestBody String xml) {
        System.out.println(xml);
        try {
            Factura factura= xmlService.convertirXMlAFactura(xml);
            return ResponseEntity.ok(factura);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/notacredito")
    public ResponseEntity<NotaCredito> getNotaCreditoByXML(@RequestBody String xml) {
        try {
            NotaCredito nc= xmlService.convertirXMlANotaCredito(xml);
            return ResponseEntity.ok(nc);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/retencion")
    public ResponseEntity<ComprobanteRetencion> getRetencionByXML(@RequestBody String xml) {
        try {
            ComprobanteRetencion retencion= xmlService.convertirXMlARetencion(xml);
            return ResponseEntity.ok(retencion);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
