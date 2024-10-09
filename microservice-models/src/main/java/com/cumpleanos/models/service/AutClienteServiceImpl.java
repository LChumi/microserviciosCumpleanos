package com.cumpleanos.models.service;

import com.cumpleanos.models.repository.AutClienteRepository;
import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.core.models.ids.AutclienteId;
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
    public Autcliente findByNroAutorizacion(String nroAutorizacion, Long empresa) {
        return repository.findById_NroAutorizaAndId_Empresa(nroAutorizacion, empresa).orElse(null);
    }
}
