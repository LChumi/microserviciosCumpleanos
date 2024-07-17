package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.ReciboPOSView;
import com.cumlpeanos.pos.repository.ReciboPOSViewRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReciboPOSViewServiceImpl implements IReciboPOSViewService{

    private final ReciboPOSViewRepositorio repositorio;

    @Override
    public ReciboPOSView findByAlmacenAndPventa(int almacen, int pventa) {
        return repositorio.findByAlmacenAndPventa(almacen, pventa)
                .orElseThrow(() -> new RuntimeException("No se encontro datos en la vista Recibo POS "));
    }
}
