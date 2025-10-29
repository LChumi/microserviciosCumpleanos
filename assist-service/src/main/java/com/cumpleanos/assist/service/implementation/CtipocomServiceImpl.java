package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.CtipocomRepository;
import com.cumpleanos.assist.service.interfaces.ICtipocomService;
import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.ids.CtipocomId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CtipocomServiceImpl extends GenericServiceImpl<Ctipocom, CtipocomId> implements ICtipocomService {
    private final CtipocomRepository repository;

    @Override
    public CrudRepository<Ctipocom, CtipocomId> getRepository() {
        return repository;
    }
}
