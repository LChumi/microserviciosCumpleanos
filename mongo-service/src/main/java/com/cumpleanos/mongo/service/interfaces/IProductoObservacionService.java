package com.cumpleanos.mongo.service.interfaces;

import com.cumpleanos.mongo.persistence.models.products.ProductoObservacion;
import com.cumpleanos.mongo.persistence.models.products.request.CorreccionRequest;

import java.util.List;

public interface IProductoObservacionService extends IGenericService<ProductoObservacion, String> {

    ProductoObservacion saveObservation(ProductoObservacion productoObservacion);

    List<ProductoObservacion> findByBodega(Long idBodega);

    ProductoObservacion addCorrection(CorreccionRequest request);
}
