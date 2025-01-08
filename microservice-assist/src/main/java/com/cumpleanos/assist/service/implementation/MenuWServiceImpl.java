package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.MenuWRepository;
import com.cumpleanos.assist.service.interfaces.IMenuWService;
import com.cumpleanos.core.models.entities.MenuW;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class MenuWServiceImpl extends GenericServiceImpl<MenuW, Long> implements IMenuWService {

    private final MenuWRepository repository;

    @Override
    public CrudRepository<MenuW, Long> getRepository() {
        return repository;
    }
}