package com.cumpleanos.ecommerce.presentation.controller;

import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;
import com.cumpleanos.ecommerce.service.implementations.WooCommerceMediaServiceImpl;
import com.cumpleanos.ecommerce.service.interfaces.WooCommerceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ecommerce")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Ecommerce", description = "Documentacion API conexion ecommerce")
public class WoocommerceController {

    private final WooCommerceService service;
    private final WooCommerceMediaServiceImpl mediaService;

    @Operation(summary = "Carga de productos", description = "Subida de productos a Woocommerce")
    @PostMapping("/woocommerce/upload-product")
    public ResponseEntity<Map<String, Object>> subirProducto(@RequestBody ProductRequest producto) {
        Map<String, Object> result = service.subirProducto(producto);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Actualizacion de productos", description = "Actualiza el producto en Woocommerce")
    @PutMapping("/woocommerce/products/{sku}/{process}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable String sku, @PathVariable Integer process, @RequestBody ProductRequest request) {
        Map<String, Object> result = service.actualizarProducto(sku, process, request);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtener Pedidos", description = "Obtener los pedidos generados en Woocommerce")
    @Parameters({
            @Parameter(name = "date", description = "Fecha de inicio", in = ParameterIn.PATH),
            @Parameter(name = "date2", description = "Fecha de fin ", in = ParameterIn.PATH)
    })
    @GetMapping("/woocommerce/orders-date/{d1}/{d2}")
    public ResponseEntity<List<PedidoWoocommerce>> getOrdersDate(@PathVariable LocalDate d1, @PathVariable LocalDate d2) {
        List<PedidoWoocommerce> listOrders = service.getOrdesrByDate(d1, d2);
        return ResponseEntity.ok(listOrders);
    }

    @Operation(summary = "Carga de imagenes", description = "Subida de imagenes de productos a Wordpresss")
    @Parameter(name = "sku", description = "Barra de producto a cargar la imagen")
    @GetMapping("/wordpress/images/{sku}")
    public ResponseEntity<Integer> uploadImage(@PathVariable String sku) throws IOException {
        Integer response = mediaService.uploadImage(sku);
        return ResponseEntity.ok(response);
    }
}
