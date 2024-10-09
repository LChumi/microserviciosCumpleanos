package com.cumpleanos.models.service;

import com.cumpleanos.models.repository.CparametRepository;
import com.cumpleanos.core.models.entities.Cparamet;
import com.cumpleanos.core.models.ids.CparametId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CparametServiceImpl extends GenericServiceImpl<Cparamet, CparametId> implements ICparametService {

    private final CparametRepository repository;

    @Override
    public CrudRepository<Cparamet, CparametId> getRepository() {
        return repository;
    }

    @Override
    public Cparamet findByValor(Long valor, Long empresa) {
        return repository.findByValorAndId_Empresa(valor, empresa).orElse(null);
    }
}
