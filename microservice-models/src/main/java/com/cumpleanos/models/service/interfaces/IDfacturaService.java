package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.DfacturaDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;

import java.math.BigInteger;

public interface IDfacturaService  extends GenericService<Dfactura, DfacturaId>{

    ServiceResponse validateQuantities(BigInteger cco, Long producto);

    DfacturaDTO getDfactura(BigInteger cco, Long producto);
}
