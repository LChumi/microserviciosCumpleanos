package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;
import com.cumpleanos.models.persistence.repository.DfacturaRepository;
import com.cumpleanos.models.service.interfaces.IDfacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DfacturaServiceImpl extends GenericServiceImpl<Dfactura, DfacturaId> implements IDfacturaService {
    private final DfacturaRepository repository;

    @Override
    public CrudRepository<Dfactura, DfacturaId> getRepository() {
        return repository;
    }
}
