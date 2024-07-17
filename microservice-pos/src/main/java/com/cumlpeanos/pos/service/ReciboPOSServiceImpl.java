package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.ReciboPOS;
import com.cumlpeanos.pos.repository.ReciboPOSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ReciboPOSServiceImpl implements IReciboPOSService{

    @Autowired
    private ReciboPOSRepository reciboPOSRepository;

    @Override
    public ReciboPOS findByIdAndEmpresa(Long id, Long empresa) {
        return reciboPOSRepository.findByIdAndEmpresa(id, empresa)
                .orElseThrow(() -> new RuntimeException("Recibo no encontrado"));
    }

    @Override
    public ReciboPOS findByCcoComproba(BigInteger ccoComproba) {
        return reciboPOSRepository.findByCcoComproba(ccoComproba)
                .orElseThrow(() -> new RuntimeException("Recibo no encontrado"));
    }
}
