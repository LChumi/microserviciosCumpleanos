package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.dtos.DatafileUsage;
import com.cumpleanos.models.service.implementation.DataFileMonitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Database Monitor", description = "Documentacion API ")
public class DatabaseMonitorController {

    private final DataFileMonitorService datafileMonitorService;

    @Operation(summary = "Espacio disponible", description = "Analiza el espacio disponible en la base de datos")
    @GetMapping("/database/monitor/database")
    public ResponseEntity<List<DatafileUsage>> listDatafileUsage() {
        List<DatafileUsage> datafileUsages = datafileMonitorService.listDatafileUsage();
        return ResponseEntity.ok(datafileUsages);
    }
}
