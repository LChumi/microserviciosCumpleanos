package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.AlmacenDTO;
import com.cumpleanos.core.models.entities.Almacen;
import com.cumpleanos.core.models.ids.AlmacenId;
import com.cumpleanos.models.service.interfaces.IAlmacenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AlmacenController {

    private final IAlmacenService service;

    @GetMapping("/almacenes/{empresa}")
    public ResponseEntity<Set<AlmacenDTO>> listarAlmacenes(@PathVariable("empresa") Long empresa) {
        Set<AlmacenDTO> almacenes = service.listByEmpresa(empresa);
        return ResponseEntity.ok(almacenes);
    }

    @GetMapping("/almacen/{empresa}/{codigo}")
    public ResponseEntity<AlmacenDTO> listarAlmacenesId(@PathVariable Long empresa, @PathVariable Long codigo) {
        AlmacenId id = new AlmacenId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);
        Almacen almacen = service.findById(id);
        AlmacenDTO alm = new AlmacenDTO(
                empresa,
                almacen.getId().getCodigo(),
                almacen.getAlmId(),
                almacen.getNombre(),
                almacen.getDireccion()
        );
        return ResponseEntity.ok(alm);
    }
}
