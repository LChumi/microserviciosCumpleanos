package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.UsrBod;
import com.cumpleanos.core.models.ids.UsrbodId;
import com.cumpleanos.models.persistence.repository.UsrBodRepository;
import com.cumpleanos.models.service.interfaces.IUsrBodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsrBodServiceImpl extends GenericServiceImpl<UsrBod, UsrbodId> implements IUsrBodService {
    private final UsrBodRepository repository;

    @Override
    public CrudRepository<UsrBod, UsrbodId> getRepository() {
        return repository;
    }

    @Override
    public Set<UsrBod> listBodByUser(Long usuario, Long empresa) {
        return repository.findById_UsuarioAndId_Empresa(usuario, empresa);
    }
}
