package com.cumpleanos.assist.presentation.controller.inventarios;

import com.cumpleanos.assist.service.implementation.inventario.PedidoDespachoService;
import com.cumpleanos.assist.service.implementation.inventario.ProductoDespachoService;
import com.cumpleanos.common.records.ServiceResponse;
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
@Tag(name = "Pedidos", description = "Documentacion Gestion Pedidos Despachos ")
public class GestionPedidosController {

    private final PedidoDespachoService service;
    private final ProductoDespachoService productoService;

    @Operation(summary = "Pedidos pendientes", description = "Lista de pedidos pendientes de despachar")
    @GetMapping("/pedidos/pendientes/{usuario}/{estado}")
    public ResponseEntity<List<FacDespedidowebV>> pedidosPendientes(
            @PathVariable String usuario,
            @PathVariable Integer estado) {

        return ResponseEntity.ok(service.pedidosPendientes(usuario, estado));
    }

    @Operation(summary = "Productos Pedidos", description = "Lista de productos pendientes de despachar")
    @GetMapping("/pedidos/despacho/productos/{empresa}/{cco}")
    public ResponseEntity<List<FacDesprodWebV>> pedidosPendientes(
            @PathVariable Long empresa,
            @PathVariable BigInteger cco,
            @RequestParam(required = false) Long hoja) {
        return ResponseEntity.ok(productoService.getProductosDespacho(empresa, cco, hoja));
    }

    @Operation(summary = "Agregar cantidad", description = "Agregar Cantidad al producto")
    @PostMapping("/pedidos/despacho/add-cantidad")
    public ResponseEntity<ServiceResponse> addCantidad(
            @RequestBody FacDesprodWebV producto) {
        ServiceResponse response = productoService.actualizarCantidad(producto);
        return ResponseEntity.ok(response);
    }

}