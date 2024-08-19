package com.cumpleanos.reccomprobantes.controller;

import com.cumpleanos.reccomprobantes.models.csv.Comprobante;
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

    @GetMapping("/read-csv")
    public List<Comprobante> readCsv(@RequestParam String filePath) {
        try {
            return csvReaderService.readCsvFile(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

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
