package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Imppartida;
import com.cumpleanos.core.models.ids.ImppartidaId;
import com.cumpleanos.models.persistence.repository.ImppartidaRepository;
import com.cumpleanos.models.service.interfaces.IImppartidaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class IImppartidaServiceImpl extends GenericServiceImpl<Imppartida, ImppartidaId> implements IImppartidaService {

    private final ImppartidaRepository repository;

    @Override
    public CrudRepository<Imppartida, ImppartidaId> getRepository() {
        return repository;
    }

    @Override
    public Imppartida getByImIdAndEmpresa(String imId, Long empresaId) {
        return repository.getByIprIdAndId_empresa(imId, empresaId);
    }
}
