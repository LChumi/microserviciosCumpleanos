package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.core.models.views.CargaProductoEcomV;

public interface ICargaProductoEcomVService extends IGenericService<CargaProductoEcomV, String> {

    CargaProductoEcomV findByProducto(Long producto);
}
