package com.cumpleanos.reccomprobantes.presentation.controller;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.service.implementation.CSVReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/recp")
@CrossOrigin("*")
@RequiredArgsConstructor()
public class CSVController {

    private final CSVReaderService csvReaderService;

    @PostMapping("/parse")
    public List<Comprobante> parseCsv(@RequestBody String csvContent) throws IOException {
        return csvReaderService.parseCsvString(csvContent);
    }
}
