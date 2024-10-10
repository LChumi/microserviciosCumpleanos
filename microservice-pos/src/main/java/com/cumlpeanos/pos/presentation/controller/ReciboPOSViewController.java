package com.cumlpeanos.pos.presentation.controller;

import com.cumlpeanos.pos.persistence.entity.ReciboPOSView;
import com.cumlpeanos.pos.service.interfaces.IReciboPOSViewService;
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
public class ReciboPOSViewController {

    private final IReciboPOSViewService service;

    @GetMapping("reciboView/{almacen}/{pventa}")
    public ResponseEntity<ReciboPOSView> getRecibo(@PathVariable Long almacen,@PathVariable Long pventa){
        try {
            ReciboPOSView reciboPOSView = service.findByAlmacenAndPventa(almacen, pventa);
            if (reciboPOSView == null ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(reciboPOSView);
        }catch (Exception e){
            log.error("ERROR al buscar en el servicio  por almacen message{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("reciboViewUsr/{usr}/{empresa}")
    public ResponseEntity<ReciboPOSView> getReciboByUsr(@PathVariable Long usr, @PathVariable Long empresa){
        try {
            ReciboPOSView reciboPOSView = service.findByUsrLiquidaAndEmpresa(usr, empresa);
            if (reciboPOSView == null ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(reciboPOSView);
        }catch (Exception e){
            log.error("ERROR al buscar en el servicio por usr_liquida message{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
