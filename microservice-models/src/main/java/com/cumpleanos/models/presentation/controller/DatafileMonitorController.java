package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.DatafileUsage;
import com.cumpleanos.models.service.implementation.DataFileMonitorService;
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
public class DatafileMonitorController {

    private final DataFileMonitorService datafileMonitorService;

    @GetMapping("/monitor/database")
    public ResponseEntity<List<DatafileUsage>> listDatafileUsage() {
        List<DatafileUsage> datafileUsages = datafileMonitorService.listDatafileUsage();
        return ResponseEntity.ok(datafileUsages);
    }
}
