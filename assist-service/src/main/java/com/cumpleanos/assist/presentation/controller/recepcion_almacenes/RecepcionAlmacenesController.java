package com.cumpleanos.assist.presentation.controller.recepcion_almacenes;

import com.cumpleanos.assist.service.implementation.recepcion_almacenes.RecepcionAlmacenesServiceImpl;
import com.cumpleanos.core.models.views.FacRevprodWebV;
import com.cumpleanos.core.models.views.FacVerifiFacingWebV;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor
@Tag(name = "Recepccion Almacenes", description = "Documentacion Recepcion de productos para almacenes")
public class RecepcionAlmacenesController {

    private final RecepcionAlmacenesServiceImpl recepcionService;

    @Operation(summary = "Lista de Comprobantes", description = "Comprobantes por Ingresar en almacenes")
    @GetMapping("/recepcion/comprobantes")
    public ResponseEntity<List<FacVerifiFacingWebV>> getComprobantes(){
        List<FacVerifiFacingWebV> comprobantes = recepcionService.getComprobantes();
        return ResponseEntity.ok(comprobantes);
    }

    @Operation(summary = "Detalles", description = "Lista de productos de un comrporbante")
    @Parameter(name = "cco", description = "Codigo del comporbantes cabecera", required = true)
    @GetMapping("/recepcion/{cco}/productos")
    public ResponseEntity<List<FacRevprodWebV>> getProductos(@PathVariable BigInteger cco){
        List<FacRevprodWebV> productos = recepcionService.detalleProductoPendientes(cco);
        return ResponseEntity.ok(productos);
    }
}
