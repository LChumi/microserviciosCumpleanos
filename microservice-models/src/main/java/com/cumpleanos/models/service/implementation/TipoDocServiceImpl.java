package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.TipoDoc;
import com.cumpleanos.models.persistence.repository.TipoDocRepository;
import com.cumpleanos.models.service.interfaces.ITipoDocService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TipoDocServiceImpl extends GenericServiceImpl<TipoDoc, Long> implements ITipoDocService {
    private final TipoDocRepository repository;


    @Override
    public CrudRepository<TipoDoc, Long> getRepository() {
        return repository;
    }

    @Override
    public Set<TipoDoc> listAllOrder() {
        return repository.findAllByOrderByTpdIdAsc();
    }
}