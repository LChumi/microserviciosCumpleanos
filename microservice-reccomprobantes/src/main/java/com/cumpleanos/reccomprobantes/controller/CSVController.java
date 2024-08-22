package com.cumpleanos.reccomprobantes.controller;

import com.cumpleanos.reccomprobantes.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.service.CSVReaderService;
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
    public List<Comprobante> parseCsv(@RequestBody String csvContent) {
        try {
            return csvReaderService.parseCsvString(csvContent);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // o maneja el error según lo necesites
        }
    }
}
