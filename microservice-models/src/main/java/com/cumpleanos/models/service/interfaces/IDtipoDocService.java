package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.dto.DTipoDocDTO;
import com.cumpleanos.core.models.entities.Dtipodoc;
import com.cumpleanos.core.models.ids.DtipodocId;

public interface IDtipoDocService extends GenericService<Dtipodoc, DtipodocId>{
    DTipoDocDTO getDtipodocByEmpresaAndTpdCodigo(Long empresa, Long tpdCodigo);
}
