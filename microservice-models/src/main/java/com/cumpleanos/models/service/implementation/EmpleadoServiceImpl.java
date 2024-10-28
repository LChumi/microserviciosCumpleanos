package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.ids.EmpleadoId;
import com.cumpleanos.models.persistence.repository.EmpleadoRepository;
import com.cumpleanos.models.service.interfaces.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado, EmpleadoId> implements IEmpleadoService {

    private final EmpleadoRepository repository;

    @Override
    public CrudRepository<Empleado, EmpleadoId> getRepository() {
        return repository;
    }

    @Override
    public Empleado findByUsuario(Long usuarioId) {
        return repository.findByUsuario(usuarioId).orElse(null);
    }
}
