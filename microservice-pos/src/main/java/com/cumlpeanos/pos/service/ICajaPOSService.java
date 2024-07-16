package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.CajaPOS;

public interface ICajaPOSService {
    CajaPOS findByAlmacenAndPventa(int almacen, int pventa);
}
