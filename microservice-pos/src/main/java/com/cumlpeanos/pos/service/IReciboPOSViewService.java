package com.cumlpeanos.pos.service;

import com.cumlpeanos.pos.models.entity.ReciboPOSView;

public interface IReciboPOSViewService {
    ReciboPOSView findByAlmacenAndPventa(int almacen, int pventa);
}
