package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.builders.ProductoPartidaBuilder;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.ProductoPartida;
import com.cumpleanos.core.models.ids.ProductoPartidaId;

public interface IProductoPartidaService extends GenericService<ProductoPartida, ProductoPartidaId> {

    ProductoPartidaBuilder getPartidaBuilder(Long producto, Long empresa);

    ServiceResponse updatePartidaDefault(Long producto, Long partida, Long empresa);
}
