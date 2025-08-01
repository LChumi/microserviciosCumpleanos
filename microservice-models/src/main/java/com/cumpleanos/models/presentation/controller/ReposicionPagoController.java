package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.ReposicionPago;
import com.cumpleanos.models.service.interfaces.IReposicionPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReposicionPagoController {

    private final IReposicionPagoService service;

    @PostMapping("/reposicion/crear-pago")
    public ResponseEntity<ReposicionPago> createPago(@RequestBody ReposicionPago reposicionPago) {
        ReposicionPago nuevoPago = service.save(reposicionPago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
    }

    @GetMapping("/reposicion/{id}/empresa/{emp}")
    public ResponseEntity<Boolean> getReposicionPago(@PathVariable Long id, @PathVariable Long emp){
        ReposicionPago found = service.getByCreposicionAndEmpresa(id, emp);
        if (found == null) {
            return ResponseEntity.ok(Boolean.FALSE);
        }
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
