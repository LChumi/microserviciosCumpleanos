package com.cumpleanos.assist.service.interfaces.ecommerce;

import com.cumpleanos.assist.service.interfaces.IGenericService;
import com.cumpleanos.core.models.views.CargaProductoEcomV;

public interface ICargaProductoEcomVService extends IGenericService<CargaProductoEcomV, String> {

    CargaProductoEcomV findByProducto(Long producto, Long empresa);
}
