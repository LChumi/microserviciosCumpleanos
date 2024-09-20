package com.cumpleanos.models.service;

import com.cumpleanos.models.repository.SistemaRepository;
import core.cumpleanos.models.entities.Sistema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SistemaServiceImp extends GenericServiceImpl<Sistema,Long> implements ISistemaService {

    private final SistemaRepository repository;

    @Override
    public CrudRepository<Sistema, Long> getRepository() {
        return repository;
    }

    @Override
    public Sistema findByRuc(String ruc) {
        return repository.findByRuc(ruc).orElse(null);
    }
}
