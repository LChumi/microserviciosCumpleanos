package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.BodegaDTO;
import com.cumpleanos.core.models.entities.Bodega;
import com.cumpleanos.core.models.ids.BodegaId;

public interface IBodegaService extends GenericService<Bodega, BodegaId>{

    BodegaDTO getBodegaWeb(Long empresa);
}
