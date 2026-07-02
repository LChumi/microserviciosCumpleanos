package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.models.service.interfaces.IPedidoHojaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "PedidoHoja", description = "Documentacion API de PedidoHoja")
public class PedidoHojaController {

    private final IPedidoHojaService service;

    @Operation(summary = "Estado Hoja",description = "Actualiza el estado de una Hoja")
    @Parameters({
            @Parameter(name = "barra", description = "Barra de prodcuto"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/hoja/{cco}/estado/{estado}")
    public ResponseEntity<ServiceResponse> getProductoByBarra(@PathVariable BigInteger ccp,
                                                              @PathVariable Long estado) {
        ServiceResponse response = service.updateEstadoHoja(ccp, estado);

        return ResponseEntity.ok(response);
    }

}