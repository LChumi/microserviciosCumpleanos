package com.cumpleanos.assist.presentation.controller.inventarios;

import com.cumpleanos.assist.service.implementation.inventario.PedidoDespachoService;
import com.cumpleanos.assist.service.implementation.inventario.ProductoDespachoService;
import com.cumpleanos.core.models.views.FacDespedidowebV;
import com.cumpleanos.core.models.views.FacDesprodWebV;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Despachos", description = "Documentacion Pedidos Despachos ")
public class PedidoDespachoController {

    private final PedidoDespachoService service;
    private final ProductoDespachoService productoService;

    @Operation(summary = "Pedidos pendientes", description = "Lista de pedidos pendientes de despachar")
    @GetMapping("/despacho/pendientes/{usuario}/{estado}")
    public ResponseEntity<List<FacDespedidowebV>> pedidosPendientes(
            @PathVariable String usuario,
            @PathVariable Integer estado){

        return ResponseEntity.ok(service.pedidosPendientes(usuario, estado));
    }

    @Operation(summary = "Productos Pedidos", description = "Lista de productos pendientes de despachar")
    @GetMapping("/despacho/productos/{empresa}/{cco}")
    public ResponseEntity<List<FacDesprodWebV>> pedidosPendientes(
            @PathVariable Long empresa,
            @PathVariable BigInteger cco,
            @RequestParam(required = false) Long hoja) {

        return ResponseEntity.ok(productoService.getProductosDespacho(empresa, cco, hoja));
    }

}
