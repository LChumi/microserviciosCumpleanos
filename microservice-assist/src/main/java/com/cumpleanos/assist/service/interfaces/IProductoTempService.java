package com.cumpleanos.assist.service.interfaces;


import com.cumpleanos.core.models.entities.ProductoTemp;

public interface IProductoTempService extends IGenericService<ProductoTemp, Long> {
    ProductoTemp getProductoTempByBarraAndEmpresa(String barra, Long empresa);
    ProductoTemp getProductoTempByCodFabricaAndEmpresa(String codFabrica, Long empresa);
    ProductoTemp getProductoTempByBarraOrItem(String barra, Long empresa);
}
