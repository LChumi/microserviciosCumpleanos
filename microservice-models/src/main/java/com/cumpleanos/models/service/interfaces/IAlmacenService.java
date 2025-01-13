package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.dto.AlmacenDTO;
import com.cumpleanos.core.models.entities.Almacen;
import com.cumpleanos.core.models.ids.AlmacenId;

import java.util.Set;

public interface IAlmacenService extends GenericService<Almacen, AlmacenId>{
    Set<AlmacenDTO> listByEmpresa(Long empresa);
}
