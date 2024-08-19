package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.PuntoVentaDTO;
import com.cumpleanos.core.models.entities.PuntoVenta;
import com.cumpleanos.core.models.ids.PuntoVentaId;

import java.util.Optional;
import java.util.Set;

public interface IPuntoVentaService extends GenericService<PuntoVenta, PuntoVentaId>{
    Set<PuntoVentaDTO> listPuntoVentaByEmpresaAndAlmacen(Long empresa, Long almacen);
    Optional<PuntoVentaDTO> getPuntoVentaById(PuntoVentaId puntoVentaId);
}
