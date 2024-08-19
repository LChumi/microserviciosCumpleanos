package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.PuntoVentaDTO;
import com.cumpleanos.core.models.ids.PuntoVentaId;
import com.cumpleanos.models.service.interfaces.IPuntoVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Punto Venta", description = "Documentacion Api Punto Venta")
public class PuntoVentaController {

    private final IPuntoVentaService service;

    @Operation(summary = "Listar Puntos de ventas", description = "Lista los puntos de ventas por almacen y empresa", tags = {"Punto Venta"}, responses = {
                @ApiResponse(responseCode = "200", description = "Lista de Punto Venta")
    })
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo empresa"),
            @Parameter(name = "almacen", description = "Codigo almacen")
    })
    @GetMapping("/punto-venta/listar/{empresa}/{almacen}")
    public ResponseEntity<Set<PuntoVentaDTO>> listarPve(@PathVariable("empresa") Long empresa, @PathVariable("almacen") Long almacen) {
        Set<PuntoVentaDTO> puntoVentas = service.listPuntoVentaByEmpresaAndAlmacen(empresa, almacen);
        return ResponseEntity.ok(puntoVentas);
    }

    @Operation(summary = "Obtener Punto de Venta", description = "Obtener el punto de venta", tags = {"Punto Venta"}, responses = {
            @ApiResponse(responseCode = "200", description = "DTO Punto de Venta")
    })
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo empresa"),
            @Parameter(name = "almacen", description = "Codigo almacen"),
            @Parameter(name = "secuencia", description = "Secuencia del Punto de Venta")
    })
    @GetMapping("/punto-venta/get/{empresa}/{almacen}/{secuencia}")
    public ResponseEntity<PuntoVentaDTO> getPuntoVenta(@PathVariable Long empresa, @PathVariable Long almacen, @PathVariable Long secuencia) {
        PuntoVentaId id = new PuntoVentaId();
        id.setEmpresa(empresa);
        id.setAlmacen(almacen);
        id.setSecuencia(secuencia);
        Optional<PuntoVentaDTO> pventa = service.getPuntoVentaById(id);
        return pventa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
