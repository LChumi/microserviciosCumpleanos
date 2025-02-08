package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import com.cumpleanos.assist.service.interfaces.IImpProdTrancitoVwService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImpProdTrancitoVwController {

    private final IImpProdTrancitoVwService service;

    @GetMapping("/buscar/prodtrancitos-v")
    public Set<ImpProdTrancitoVw> buscar(
            @RequestParam(required = false) Long empresa,
            @RequestParam(required = false) String nroComprobante,
            @RequestParam(required = false) String observacion,
            @RequestParam(required = false) Long proveedor,
            @RequestParam(required = false)LocalDate fecha,
            @RequestParam(required = false) String estado
            ){
        return service.find(empresa, nroComprobante, observacion, proveedor, fecha, estado);
    }
}
