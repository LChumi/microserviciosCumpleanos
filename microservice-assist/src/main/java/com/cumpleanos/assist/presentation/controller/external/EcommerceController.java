package com.cumpleanos.assist.presentation.controller.external;

import com.cumpleanos.assist.service.interfaces.ecommerce.IPedidosEcommerceService;
import com.cumpleanos.assist.service.interfaces.ecommerce.IProductosEcommerceService;
import com.cumpleanos.assist.service.interfaces.ecommerce.IStockEcommerceVService;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.views.StockEcommerceV;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Ecommerce", description = "Documentacion de servicios sincronizacion de Ecommerce con el sistema")
public class EcommerceController {

    private final IProductosEcommerceService serviceProductsEcommerce;
    private final IStockEcommerceVService serviceStockEcommerce;
    private final IPedidosEcommerceService servicePedidosEcommerce;

    @Operation(summary = "Sube productos del sistema a ecommerce")
    @Parameter(name = "id", description = "Codigo de Producto")
    @Parameter(name = "empresa", description = "Codigo de empresa")
    @GetMapping("/ecommerce/producto-save/{id}/{empresa}")
    public ResponseEntity<ServiceResponse> getProducto(@PathVariable Long id, @PathVariable Long empresa) {
        ServiceResponse p = serviceProductsEcommerce.uploadProductEcommerce(id, empresa);
        return ResponseEntity.ok(p);
    }

    @Operation(summary = "Actualizacion de producto detalle o imagen")
    @Parameter(name = "id", description = "Codigo de producto")
    @Parameter(name = "empresa", description = "Codigo empresa")
    @Parameter(name = "sku", description = "Barra anterior del producto")
    @Parameter(name = "process", description = "Proceso actualizacion 0 actualiza producto , 1 actualiza producto e imagen ")
    @GetMapping("/ecommerce/product-update/{id}/{empresa}/{sku}/{process}")
    public ResponseEntity<ServiceResponse> getProductoUpdate(@PathVariable Long id, @PathVariable Long empresa, @PathVariable String sku, @PathVariable Integer process) {
        ServiceResponse response = serviceProductsEcommerce.updateProductEcommerce(id, sku, empresa, process);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Sincronizacion de Stocks del sistema a ecommerce")
    @GetMapping("/ecommerce/stock/view")
    public ResponseEntity<List<StockEcommerceV>> findAll() {
        List<StockEcommerceV> lista = serviceStockEcommerce.findAll();
        log.info(lista.toString());
        return ResponseEntity.ok(lista);
    }

    @Operation(summary = "Sincronizacion de pedidos de ecommerce al sistema")
    @GetMapping("/ecommerce/orders-sync")
    public ResponseEntity<ServiceResponse> syncOrders() {
        ServiceResponse response = servicePedidosEcommerce.getPedidosAndUpdateSystem();
        return ResponseEntity.ok(response);
    }
}