package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.persistence.repository.SistemaRepository;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.models.service.interfaces.ISistemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
