package com.cumpleanos.pos.service.implementation;

import com.cumpleanos.pos.persistence.entity.ReciboPOSView;
import com.cumpleanos.pos.persistence.repository.ReciboPOSViewRepositorio;
import com.cumpleanos.pos.service.interfaces.IReciboPOSViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReciboPOSViewServiceImpl implements IReciboPOSViewService {

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
