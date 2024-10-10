package com.cumpleanos.pos.service.interfaces;

import com.cumpleanos.pos.persistence.entity.CajaPOS;

public interface ICajaPOSService {
    CajaPOS findByAlmacenAndPventa(Long almacen, Long pventa);
}
