package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.clients.ModelsClient;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelsServiceImpl{

    private final ModelsClient modelsClient;

    public Sistema getEmpresaByRuc(String ruc){
        return modelsClient.findByRuc(ruc).getBody();
    }

    public SriDocEleEmi getSriDocByClaveAcceso(String claveAcceso){
        return modelsClient.findByClaveAcceso(claveAcceso).getBody();
    }
}
