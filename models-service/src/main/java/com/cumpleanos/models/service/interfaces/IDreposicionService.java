package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.DreposicionDTO;
import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.DreposicionId;

import java.util.List;

public interface IDreposicionService extends GenericService<Dreposicion, DreposicionId> {

    DreposicionDTO saveDetail(Dreposicion d);

    List<DreposicionDTO> getProductsByCreposicion(Long creposicion);

    DreposicionDTO getByCreposicionAndProducto(Long creposicion, String barra, Long empresa);
}