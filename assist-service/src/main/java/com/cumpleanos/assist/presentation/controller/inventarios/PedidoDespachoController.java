package com.cumpleanos.assist.presentation.controller.inventarios;

import com.cumpleanos.assist.service.implementation.inventario.PedidoDespachoService;
import com.cumpleanos.core.models.views.FacDespedidowebV;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Despachos", description = "Documentacion Pedidos Despachos ")
public class PedidoDespachoController {

    private final PedidoDespachoService service;

    @Operation(summary = "Pedidos pendientes", description = "Lista de pedidos pendietes de despachar")
    @GetMapping("/despacho/pendientes/{usuario}/{estado}")
    public ResponseEntity<List<FacDespedidowebV>> pedidosPendientes(@PathVariable String usuario,@PathVariable Integer estado){
        return ResponseEntity.ok(service.pedidosPendientes(usuario, estado));
    }

}
