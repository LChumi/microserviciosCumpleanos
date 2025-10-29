package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.DtipocomRepository;
import com.cumpleanos.assist.service.interfaces.IDtipocomService;
import com.cumpleanos.core.models.entities.Dtipocom;
import com.cumpleanos.core.models.ids.DtipocomId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DtipocomServiceImpl extends GenericServiceImpl<Dtipocom, DtipocomId> implements IDtipocomService {
    private final DtipocomRepository repository;

    @Override
    public CrudRepository<Dtipocom, DtipocomId> getRepository() {
        return repository;
    }
}
