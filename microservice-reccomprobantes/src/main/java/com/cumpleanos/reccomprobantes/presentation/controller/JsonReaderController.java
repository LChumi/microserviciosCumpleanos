package com.cumpleanos.reccomprobantes.presentation.controller;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.service.implementation.JsonReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recp")
@CrossOrigin("*")
@RequiredArgsConstructor
public class JsonReaderController {

    private final JsonReaderService jsonReaderService;

    @PostMapping("/json")
    private ResponseEntity<Comprobante> convertirStringAComprobante(@RequestBody String json) {
        Comprobante comprobante = jsonReaderService.convertirStringJsonToComprobante(json);
        return ResponseEntity.ok(comprobante);
    }
}
