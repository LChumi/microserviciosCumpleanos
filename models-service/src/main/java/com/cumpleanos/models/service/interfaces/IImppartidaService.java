package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Imppartida;
import com.cumpleanos.core.models.ids.ImppartidaId;

public interface IImppartidaService extends GenericService<Imppartida, ImppartidaId> {

    Imppartida getByImIdAndEmpresa(String imId, Long empresaId);
}
