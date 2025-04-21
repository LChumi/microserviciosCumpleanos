package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.DreposicionId;

import java.util.List;

public interface IDreposicionService extends GenericService<Dreposicion, DreposicionId> {

    List<Dreposicion> getProductsByCreposicion(Long creposicion);
}
