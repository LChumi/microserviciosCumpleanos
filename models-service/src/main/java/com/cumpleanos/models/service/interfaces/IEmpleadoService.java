package com.cumpleanos.models.service.interfaces;

import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.ids.EmpleadoId;

public interface IEmpleadoService extends GenericService<Empleado, EmpleadoId> {
    Empleado findByUsuario(Long usuarioId);
}
