package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.core.models.ids.AlmacenId;
import com.cumpleanos.models.service.interfaces.IAlmacenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Almacen", description = "Documentacion API alamacenes")
public class AlmacenController {

    private final IAlmacenService service;

    @Operation(summary = "Lista Almacenes", description = "Lista de almacenes por empresa")
    @Parameter(name = "Empresa", description = "Codigo de empresa")
    @GetMapping("/almacen/{empresa}")
    public ResponseEntity<Set<AlmacenDTO>> listarAlmacenes(@PathVariable("empresa") Long empresa) {
        Set<AlmacenDTO> almacenes = service.listByEmpresa(empresa);
        return ResponseEntity.ok(almacenes);
    }

    @Operation(summary = "Obtener Almacen", description = "Obtener informacion de almacen por codigo y empresa")
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo de empresa"),
            @Parameter(name = "codigo", description = "Codigo de almacen")
    })
    @GetMapping("/almacen/get/{empresa}/{codigo}")
    public ResponseEntity<AlmacenDTO> getById(@PathVariable Long empresa, @PathVariable Long codigo){
        AlmacenId id = new AlmacenId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);
        Optional<AlmacenDTO> almacen = service.getById(id);
        return almacen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
