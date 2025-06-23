package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.dto.ProductoDTO;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;

public interface IProductoService extends IGenericService<Producto, ProductoId> {

    ProductoDTO getProductoByBarraAndEmpresa(String barra, Long empresa);

}
