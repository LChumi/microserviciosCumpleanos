package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Ubicacion;
import com.cumpleanos.models.service.interfaces.IUbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UbicacionController {

    private final IUbicacionService service;

    @GetMapping("/ubicacion/{emp}/{nombre}")
    public ResponseEntity<List<Ubicacion>> getUbicacionByNombre(@PathVariable Long emp, @PathVariable String nombre) {
        List<Ubicacion> founds = service.findByEmpresaAndNombre(emp, nombre);
        if (founds == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(founds);
    }
}
