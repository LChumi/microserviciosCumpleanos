package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.clients.SistemaClient;
import com.cumpleanos.reccomprobantes.clients.SriDocEleEmiClient;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModelsServiceImpl{

    private final SistemaClient sistemaClient;
    private final SriDocEleEmiClient sriDocClient;

    public Sistema getEmpresaByRuc(String ruc){
        return sistemaClient.findByRuc(ruc).getBody();
    }

    public SriDocEleEmi getSriDocByClaveAcceso(String claveAcceso){
        return sriDocClient.findByClaveAcceso(claveAcceso).getBody();
    }
}
