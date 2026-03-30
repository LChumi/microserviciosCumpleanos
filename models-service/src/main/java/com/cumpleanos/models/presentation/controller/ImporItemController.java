package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ImporItemDTO;
import com.cumpleanos.models.service.interfaces.IImporitemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor
@Tag(name = "Productos Importacion", description = "Detalles de productos de Importacion")
public class ImporItemController {

    private final IImporitemService service;

    @Operation(summary = "Obtener producto", description = "Obtiene el producto de importacion ")
    @Parameters({
            @Parameter(name = "cco", description = "Codigo de importacion"),
            @Parameter(name = "producto", description = "Codigo del producto")
    })
    @GetMapping("/imporitem/getByCco/{cco}/{producto}")
    public ResponseEntity<ImporItemDTO> getProductoImpor(@PathVariable BigInteger cco , @PathVariable Long producto){
        return null;
    }

}