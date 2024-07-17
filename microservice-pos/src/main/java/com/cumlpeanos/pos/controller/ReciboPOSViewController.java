package com.cumlpeanos.pos.controller;

import com.cumlpeanos.pos.models.entity.ReciboPOSView;
import com.cumlpeanos.pos.service.IReciboPOSViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/reciboView/")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ReciboPOSViewController {

    private final IReciboPOSViewService service;

    @GetMapping("almacen/{almacen}/pventa/{pventa}")
    public ResponseEntity<ReciboPOSView> getRecibo(@PathVariable int almacen,@PathVariable int pventa){
        try {
            ReciboPOSView reciboPOSView = service.findByAlmacenAndPventa(almacen, pventa);
            if (reciboPOSView == null ){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(reciboPOSView);
        }catch (Exception e){
            log.error("ERROR al buscar en el servicio message{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
