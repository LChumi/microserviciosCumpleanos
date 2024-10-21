package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.pos.persistence.entity.CajaPOS;
import com.cumpleanos.pos.service.interfaces.ICajaPOSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pos/")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class CajaPOSController {

    private final ICajaPOSService cajaPOSService;

    @GetMapping("cajapos/{almacen}/{pventa}")
    public ResponseEntity<CajaPOS> porAlmacenYPventa(@PathVariable Long almacen, @PathVariable Long pventa) {
        CajaPOS cajaPOS= cajaPOSService.findByAlmacenAndPventa(almacen, pventa);
        if (cajaPOS == null ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(cajaPOS);
    }
}
