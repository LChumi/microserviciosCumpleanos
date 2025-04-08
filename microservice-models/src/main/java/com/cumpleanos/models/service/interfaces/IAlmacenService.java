package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.core.models.entities.Almacen;
import com.cumpleanos.core.models.ids.AlmacenId;

import java.util.Optional;
import java.util.Set;

public interface IAlmacenService extends GenericService<Almacen, AlmacenId>{
    Set<AlmacenDTO> listByEmpresa(Long empresa);
    Optional<AlmacenDTO> getById(AlmacenId id);
}
