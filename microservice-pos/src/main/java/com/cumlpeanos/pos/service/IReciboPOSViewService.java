package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.ReciboPOSView;

public interface IReciboPOSViewService {
    ReciboPOSView findByAlmacenAndPventa(Long almacen, Long pventa);
    ReciboPOSView findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);
}
