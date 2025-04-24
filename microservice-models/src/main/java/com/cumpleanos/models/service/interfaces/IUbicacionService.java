package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Ubicacion;
import com.cumpleanos.core.models.ids.UbicacionId;

import java.util.List;

public interface IUbicacionService  extends GenericService<Ubicacion, UbicacionId>{

    List<Ubicacion> findByEmpresaAndNombre(Long empresa, String nombre);
}
