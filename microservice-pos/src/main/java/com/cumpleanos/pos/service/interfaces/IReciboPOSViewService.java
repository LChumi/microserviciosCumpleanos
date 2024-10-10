package com.cumlpeanos.pos.service.interfaces;

import com.cumlpeanos.pos.persistence.entity.ReciboPOSView;

public interface IReciboPOSViewService {
    ReciboPOSView findByAlmacenAndPventa(Long almacen, Long pventa);
    ReciboPOSView findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);
}
