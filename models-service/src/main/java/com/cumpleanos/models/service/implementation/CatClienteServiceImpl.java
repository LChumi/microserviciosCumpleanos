package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.CatCliente;
import com.cumpleanos.core.models.ids.CatClienteId;
import com.cumpleanos.models.persistence.repository.CatClienteRepository;
import com.cumpleanos.models.service.interfaces.ICatClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CatClienteServiceImpl extends GenericServiceImpl<CatCliente, CatClienteId> implements ICatClienteService {

    private final CatClienteRepository repository;


    @Override
    public CrudRepository<CatCliente, CatClienteId> getRepository() {
        return repository;
    }

    @Override
    public Set<CatCliente> listByEmpresa(Long empresa) {
        return repository.findById_Empresa(empresa);
    }
}
