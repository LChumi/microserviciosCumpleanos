package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;

public interface IProductoService  extends GenericService<Producto, ProductoId>{

    ProductoDTO getProductoByBarraAndEmpresa(String barra, Long empresa);

}
