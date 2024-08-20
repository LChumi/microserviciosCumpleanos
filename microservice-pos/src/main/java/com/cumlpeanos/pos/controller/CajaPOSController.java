package com.cumlpeanos.pos.controller;

import com.cumlpeanos.pos.models.entity.CajaPOS;
import com.cumlpeanos.pos.service.ICajaPOSService;
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
    public ResponseEntity<CajaPOS> porAlmacenYPventa(@PathVariable int almacen, @PathVariable int pventa) {
        try {
            CajaPOS cajaPOS= cajaPOSService.findByAlmacenAndPventa(almacen, pventa);
            if (cajaPOS == null ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(cajaPOS);
        }catch (Exception e){
            log.error("ERROR en el servicio buscar por almacen y pventa: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
