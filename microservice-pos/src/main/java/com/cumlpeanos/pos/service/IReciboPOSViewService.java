package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.ReciboPOSView;

import java.util.Map;

public interface IReciboPOSViewService {
    ReciboPOSView findByAlmacenAndPventa(int almacen, int pventa);
    ReciboPOSView findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);
    Map<String,String> listarPuertos(Long usrLiquida, Long empresa);
}
