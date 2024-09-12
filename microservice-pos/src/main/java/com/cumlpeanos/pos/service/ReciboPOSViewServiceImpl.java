package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.ReciboPOSView;
import com.cumlpeanos.pos.repository.ReciboPOSViewRepositorio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReciboPOSViewServiceImpl implements IReciboPOSViewService{

    private final ReciboPOSViewRepositorio repositorio;

    @Override
    public ReciboPOSView findByAlmacenAndPventa(Long almacen, Long pventa) {
        return repositorio.findByAlmacenAndPventa(almacen, pventa)
                .orElseThrow(() -> new RuntimeException("No se encontro datos en la vista Recibo POS "));
    }

    @Override
    public ReciboPOSView findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa) {
        return repositorio.findByUsrLiquidaAndEmpresa(usrLiquida, empresa)
                .orElseThrow(() -> new RuntimeException("No se encontro datos en la vista Recibo POS "));
    }
}
