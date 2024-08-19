package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.ids.PedidoHojaId;
import com.cumpleanos.models.service.interfaces.IPedidoHojaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "PedidoHoja", description = "Documentacion API de PedidoHoja")
public class PedidoHojaController {

    private final IPedidoHojaService service;

    @Operation(summary = "Estado Hoja",description = "Actualiza el estado de una Hoja")
    @Parameter(name = "estado", description = "Estado Hoja")
    @PutMapping("/pedido-hoja/{estado}/")
    public ResponseEntity<ServiceResponse> updateHojaEstado(@RequestBody PedidoHojaId id, @PathVariable Long estado) {
        ServiceResponse response = service.updateEstadoHoja(id, estado);
        return ResponseEntity.ok(response);
    }

}