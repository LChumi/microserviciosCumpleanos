package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.service.interfaces.IReciboPOSViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pos/")
@Slf4j
@RequiredArgsConstructor
public class ReciboPOSViewController {

    private final IReciboPOSViewService service;

    @GetMapping("reciboView/{almacen}/{pventa}")
    public ResponseEntity<ReciboPOSView> getRecibo(@PathVariable Long almacen,@PathVariable Long pventa){
        ReciboPOSView reciboPOSView = service.findByAlmacenAndPventa(almacen, pventa);
        if (reciboPOSView == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(reciboPOSView);
    }

    @GetMapping("reciboViewUsr/{usr}/{empresa}")
    public ResponseEntity<ReciboPOSView> getReciboByUsr(@PathVariable Long usr, @PathVariable Long empresa){
        ReciboPOSView reciboPOSView = service.findByUsrLiquidaAndEmpresa(usr, empresa);
        if (reciboPOSView == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(reciboPOSView);
    }
}
