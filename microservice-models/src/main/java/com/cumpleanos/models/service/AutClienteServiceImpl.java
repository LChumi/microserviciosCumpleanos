package com.cumpleanos.models.service;

import com.cumpleanos.models.repository.AutClienteRepository;
import core.cumpleanos.models.entities.Autcliente;
import core.cumpleanos.models.ids.AutclienteId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutClienteServiceImpl extends GenericServiceImpl<Autcliente, AutclienteId> implements IAutClienteService {

    private final AutClienteRepository repository;

    @Override
    public CrudRepository<Autcliente, AutclienteId> getRepository() {
        return repository;
    }

    @Override
    public Autcliente findByNroAutorizacion(String nroAutorizacion) {
        return repository.findById_NroAutoriza(nroAutorizacion).orElse(null);
    }
}
