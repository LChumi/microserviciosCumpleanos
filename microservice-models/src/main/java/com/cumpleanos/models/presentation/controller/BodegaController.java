package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.BodegaDTO;
import com.cumpleanos.models.service.interfaces.IBodegaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BodegaController {

    private final IBodegaService bodegaService;

    @GetMapping("/bodega/web/{empresa}")
    public BodegaDTO getBodegaWeb(@PathVariable Long empresa) {
        return bodegaService.getBodegaWeb(empresa);
    }

}
