package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.ClienteRepository;
import com.cumpleanos.assist.service.interfaces.IClienteService;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClienteServiceImpl extends GenericServiceImpl<Cliente, ClienteId> implements IClienteService {

    private final ClienteRepository repository;

    @Override
    public CrudRepository<Cliente, ClienteId> getRepository() {
        return repository;
    }

    @Override
    public Set<Cliente> findByTipoAndEmpresa(Short tipo, Long empresa) {
        return repository.findById_EmpresaAndTipo(empresa, tipo);
    }

}
