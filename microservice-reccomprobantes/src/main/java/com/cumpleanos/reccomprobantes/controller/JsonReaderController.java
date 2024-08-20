package com.cumpleanos.reccomprobantes.controller;

import com.cumpleanos.reccomprobantes.models.json.ComprobanteJson;
import com.cumpleanos.reccomprobantes.models.xml.ComprobanteXml;
import com.cumpleanos.reccomprobantes.service.JsonReaderService;
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
    private ResponseEntity<ComprobanteXml> convertirStringAComprobante(@RequestBody String json) {
        try {
            ComprobanteXml comprobante = jsonReaderService.convertirStringJsonToComprobante(json);
            return ResponseEntity.ok(comprobante);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
