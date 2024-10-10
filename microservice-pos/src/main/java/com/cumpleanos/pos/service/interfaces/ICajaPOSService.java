package com.cumlpeanos.pos.service.interfaces;

import com.cumlpeanos.pos.persistence.entity.CajaPOS;

public interface ICajaPOSService {
    CajaPOS findByAlmacenAndPventa(Long almacen, Long pventa);
}
