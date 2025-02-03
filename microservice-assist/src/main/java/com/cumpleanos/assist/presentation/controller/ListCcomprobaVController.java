package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.views.ListCcomprobaV;
import com.cumpleanos.assist.service.implementation.ListCcomprobaServiceImpl;
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
public class ListCcomprobaVController {

    private final ListCcomprobaServiceImpl service;

    @GetMapping("/buscar")
    public Set<ListCcomprobaV> buscar(
            @RequestParam(required = false) Long empresa,
            @RequestParam(required = false) Short periodo,
            @RequestParam(required = false) LocalDate fecha,
            @RequestParam(required = false) Short mes,
            @RequestParam(required = false) Long sigla,
            @RequestParam(required = false) Long almacen,
            @RequestParam(required = false) Long serie,
            @RequestParam(required = false) Long numero,
            @RequestParam(required = false) String concepto,
            @RequestParam(required = false) String doctran,
            @RequestParam(required = false) Long estado,
            @RequestParam(required = false) Long tipodoc
            ) {
        return service.find(empresa, periodo, fecha, mes, sigla, almacen, serie, numero, concepto, doctran, estado, tipodoc);
    }
}