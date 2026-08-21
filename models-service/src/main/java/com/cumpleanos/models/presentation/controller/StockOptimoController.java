package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.StockOptimo;
import com.cumpleanos.models.persistence.dto.MinMaxUpdateRequest;
import com.cumpleanos.models.service.interfaces.IStockOptimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "StockOptimo", description = "Documentacion API StockOptimo")
public class StockOptimoController {

    private final IStockOptimoService service;

    @Operation(summary = "Crear StockOptimo", description = "Crear Stock optimo de un proucto y bodega")
    @PostMapping("/stockoptimo/crear")
    public ResponseEntity<StockOptimo> save (@RequestBody StockOptimo stockOptimo){
        StockOptimo save = service.save(stockOptimo);
        return ResponseEntity.ok().body(save);
    }

    @Operation(summary = "Actualizar stock", description = "Actualiza minimos y maximos de stocks de productos")
    @PutMapping("/stockoptimo/update")
    public ResponseEntity<ServiceResponse> updateMaximos(@RequestBody MinMaxUpdateRequest request){
        ServiceResponse response = service.updateMinMax(request);
        return ResponseEntity.ok(response);
    }
}
