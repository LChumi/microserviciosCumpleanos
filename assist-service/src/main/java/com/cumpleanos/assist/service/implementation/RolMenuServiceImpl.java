package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.RolMenuRepository;
import com.cumpleanos.assist.service.interfaces.IRolMenuService;
import com.cumpleanos.core.models.entities.RolMenu;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RolMenuServiceImpl extends GenericServiceImpl<RolMenu, Long> implements IRolMenuService {

    private final RolMenuRepository repository;

    @Override
    public CrudRepository<RolMenu, Long> getRepository() {
        return repository;
    }
}