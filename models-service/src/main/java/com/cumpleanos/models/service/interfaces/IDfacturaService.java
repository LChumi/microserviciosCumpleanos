package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.DfacturaDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface IDfacturaService extends GenericService<Dfactura, DfacturaId> {

    List<DfacturaDTO> getDfacturas(BigInteger cco, Long producto);

    ServiceResponse addCantApr(BigInteger cco, Long producto, Integer cantidad, BigDecimal precioReferencia);
}
