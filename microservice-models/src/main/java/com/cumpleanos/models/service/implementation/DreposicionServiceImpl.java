package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.models.persistence.repository.DreposicionRepository;
import com.cumpleanos.models.service.interfaces.IDreposicionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DreposicionServiceImpl extends GenericServiceImpl<Dreposicion, DreposicionId> implements IDreposicionService {

    private final DreposicionRepository repository;

    @Override
    public CrudRepository<Dreposicion, DreposicionId> getRepository() {
        return repository;
    }

    @Override
    public List<Dreposicion> getProductsByCreposicion(Long creposicion) {
        return repository.findByCreposicionId(creposicion);
    }
}
