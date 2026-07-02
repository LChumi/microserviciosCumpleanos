package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dmovinv;
import com.cumpleanos.core.models.ids.DmovinvId;

import java.math.BigInteger;

public interface IDmovinvSerice extends GenericService<Dmovinv, DmovinvId> {

    ServiceResponse actualizarCantidadDespachada(BigInteger cco, Long producto, Integer cantidad);
}
