package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.pos.persistence.entity.ReciboPOS;
import com.cumpleanos.pos.persistence.ids.ReciboPOSId;
import com.cumpleanos.pos.persistence.repository.ReciboPOSRepository;
import com.cumpleanos.pos.service.interfaces.IReciboPOSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ReciboPOSServiceImpl implements IReciboPOSService {

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

    @Override
    public ReciboPOS getReciboPOS(ReciboPOSId id) {
        ReciboPOS reciboPOS= reciboPOSRepository.findById(id).orElseThrow(() -> new RuntimeException("Recibo no encontrado"));
        reciboPOS.getTipoCreditoPOS().getId();
        System.out.println(reciboPOS.getTipoCreditoPOS().getId());
        return reciboPOS;
    }
}
