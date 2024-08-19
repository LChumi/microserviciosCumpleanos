package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.dtos.ComprobanteDetalleProductoDTO;
import com.cumpleanos.models.service.interfaces.IComprobanteDetalleProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Comprobante Detalle", description = "Documentacion API Comprobantes cabecera y detalle")
public class ComprobanteDetalleController {

    private final IComprobanteDetalleProductoService solicitudCompraService;

    @Operation(summary = "Obtener comprobante", description = "Obtiene el comprobante con su liosta de items", tags = {"Comprobante Detalle"}, responses = {
            @ApiResponse(responseCode = "200", description = "DTO comprobante")
    })
    @Parameter(name = "cco", description = "codigo cco")
    @GetMapping("comprobante-detalle/{cco}/productos")
    public ResponseEntity<ComprobanteDetalleProductoDTO> verSolicitudCompro(@PathVariable("cco") BigInteger cco) {
        ComprobanteDetalleProductoDTO response = solicitudCompraService.getComprobanteDetalleProducto(cco);
        return ResponseEntity.ok(response);
    }
}
