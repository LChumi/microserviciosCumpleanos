package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Ccomproba;
import com.cumpleanos.core.models.ids.CcomprobaId;

import java.math.BigInteger;

public interface ICcomprobaService  extends GenericService<Ccomproba, CcomprobaId>{
    Boolean updateBodegaCco(Long empresa, BigInteger codigo, Long bodega, String observacion);
}
