package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.ImportacionDTO;
import com.cumpleanos.core.models.entities.Importacion;
import com.cumpleanos.core.models.ids.ImportacionId;

import java.math.BigInteger;

public interface IImportacionService extends GenericService<Importacion, ImportacionId> {

    ImportacionDTO getById(BigInteger cco , Long empresa);

}