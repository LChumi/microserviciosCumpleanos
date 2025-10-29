package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.RolWrepository;
import com.cumpleanos.assist.service.interfaces.IRolWService;
import com.cumpleanos.core.models.entities.RolW;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RolWServiceImpl extends GenericServiceImpl<RolW, Long> implements IRolWService {

    private final RolWrepository repository;

    @Override
    public CrudRepository<RolW, Long> getRepository() {
        return repository;
    }
}