package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.dto.CtipocomDTO;
import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.ids.CtipocomId;

import java.util.Set;

public interface ICtipocomService extends GenericService<Ctipocom, CtipocomId> {
    Set<CtipocomDTO> listCtipocomByEmpresa(Long empresaId);
}
