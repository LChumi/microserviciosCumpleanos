package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.PuntoVentaDTO;
import com.cumpleanos.core.models.ids.PuntoVentaId;
import com.cumpleanos.models.service.interfaces.IPuntoVentaService;
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
public class PuntoVentaController {

    private final IPuntoVentaService service;

    @GetMapping("/punto-venta/listar/{empresa}/{almacen}")
    public ResponseEntity<Set<PuntoVentaDTO>> listarPve(@PathVariable("empresa") Long empresa, @PathVariable("almacen") Long almacen) {
        Set<PuntoVentaDTO> puntoVentas = service.listPuntoVentaByEmpresaAndAlmacen(empresa, almacen);
        return ResponseEntity.ok(puntoVentas);
    }

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
