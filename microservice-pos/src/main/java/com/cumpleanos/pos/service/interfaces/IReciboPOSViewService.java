package com.cumpleanos.pos.service.interfaces;

import com.cumpleanos.pos.persistence.entity.ReciboPOSView;

public interface IReciboPOSViewService {
    ReciboPOSView findByAlmacenAndPventa(Long almacen, Long pventa);
    ReciboPOSView findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);
}
