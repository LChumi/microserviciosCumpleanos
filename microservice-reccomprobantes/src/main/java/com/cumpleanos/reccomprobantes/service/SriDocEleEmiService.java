package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.models.models.entities.SriDocEleEmi;
import com.cumpleanos.models.repository.SriDocEleEmiRepository;
import org.springframework.stereotype.Service;

@Service
public class SriDocEleEmiService {

    private SriDocEleEmiRepository sriDocEleEmiRepository;

    public SriDocEleEmi findByClaveAcceso(String claveAcceso) {
        return sriDocEleEmiRepository.findBySriClaveAcceso(claveAcceso);
    }
}
