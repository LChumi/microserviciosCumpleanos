package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.entity.ProductoTemp;

public interface IProductoTempService extends IGenericService<ProductoTemp, Long> {
    ProductoTemp getProductoTempByBarraAndEmpresa(String barra, Long empresa);
    ProductoTemp getProductoTempByBarraOrItem(String barra, Long empresa);
}
