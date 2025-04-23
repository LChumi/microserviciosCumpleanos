package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.ReposicionPago;
import com.cumpleanos.core.models.ids.ReposicionPagoId;

public interface IReposicionPagoService extends GenericService<ReposicionPago, ReposicionPagoId>{

    ReposicionPago getByCreposicionAndEmpresa(Long creposicion, Long empresa);
}
