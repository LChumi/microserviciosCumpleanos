package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.PedidoHoja;
import com.cumpleanos.core.models.ids.PedidoHojaId;

import java.math.BigInteger;

public interface IPedidoHojaService extends GenericService<PedidoHoja, PedidoHojaId> {

    ServiceResponse updateEstadoHoja(PedidoHojaId id, Long estado);
}
