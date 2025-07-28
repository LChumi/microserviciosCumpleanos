package com.cumpleanos.assist.service.interfaces.importaciones;

import com.cumpleanos.assist.persistence.dto.OrdenCompraListDTO;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;

public interface IOrdenCompraImportacionService {

    ProductImportTransformer generateOrder(OrdenCompraListDTO orden);
}
