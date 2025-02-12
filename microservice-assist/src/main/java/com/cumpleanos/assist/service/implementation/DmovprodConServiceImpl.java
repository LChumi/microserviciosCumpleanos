package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.DmovprodConRepository;
import com.cumpleanos.assist.service.interfaces.IDmovprodConService;
import com.cumpleanos.core.models.entities.DmovprodCon;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DmovprodConServiceImpl extends GenericServiceImpl<DmovprodCon, Long> implements IDmovprodConService {
    private final DmovprodConRepository repository;

    @Override
    public CrudRepository<DmovprodCon, Long> getRepository() {
        return repository;
    }
}
