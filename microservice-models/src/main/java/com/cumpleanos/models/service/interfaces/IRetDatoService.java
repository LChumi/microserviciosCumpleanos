package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.RetDato;
import com.cumpleanos.core.models.ids.RetDatoId;

public interface IRetDatoService  extends GenericService<RetDato, RetDatoId>{
    RetDato getRetDatoByEmpresaTablacoaId(Long empresa , Long tablacoa, String id);
}
