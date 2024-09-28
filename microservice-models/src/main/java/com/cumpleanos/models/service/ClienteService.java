package com.cumpleanos.models.service;

import com.cumpleanos.models.repository.ClienteRepository;
import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.ids.ClienteId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService extends GenericServiceImpl<Cliente, ClienteId> implements IClienteService {

    private final ClienteRepository repository;

    @Override
    public CrudRepository<Cliente, ClienteId> getRepository() {
        return repository;
    }

    @Override
    public Cliente findByCedulaRucAndEmpresa(String cedula, Long empresa) {
        return repository.findById_EmpresaAndRucCedulaAndTipo(empresa, cedula, (short) 2).orElse(null);
    }
}
