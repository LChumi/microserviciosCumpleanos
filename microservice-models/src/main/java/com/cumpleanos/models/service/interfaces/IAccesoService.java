package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.AccesoDTO;
import com.cumpleanos.core.models.entities.Acceso;
import com.cumpleanos.core.models.ids.AccesoId;

public interface IAccesoService extends GenericService<Acceso, AccesoId>{
    AccesoDTO getAccesoByUsuarioAndEmpresa(Long usuario, Long empresa);
}
