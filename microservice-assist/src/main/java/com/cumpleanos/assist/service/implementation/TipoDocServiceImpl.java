package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.TipoDocRepository;
import com.cumpleanos.assist.service.interfaces.ITipoDocService;
import com.cumpleanos.core.models.entities.TipoDoc;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TipoDocServiceImpl extends GenericServiceImpl<TipoDoc, Long> implements ITipoDocService {

    private final TipoDocRepository repository;

    @Override
    public CrudRepository<TipoDoc, Long> getRepository() {
        return repository;
    }

}
