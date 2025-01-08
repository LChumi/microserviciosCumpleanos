package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.persistence.repository.CparametRepository;
import com.cumpleanos.core.models.entities.Cparamet;
import com.cumpleanos.core.models.ids.CparametId;
import com.cumpleanos.models.service.interfaces.ICparametService;
import com.cumpleanos.models.utils.enums.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
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

    @Override
    public Cparamet save(Cparamet entity) {
        Long codigo = getNextSequenceValue(Sequence.CPARAMETCODIGO);
        entity.getId().setCodigo(codigo);
        return super.save(entity);
    }
}
