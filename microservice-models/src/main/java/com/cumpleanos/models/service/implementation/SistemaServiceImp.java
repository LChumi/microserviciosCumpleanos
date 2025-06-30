package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.persistence.repository.SistemaRepository;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.models.service.interfaces.ISistemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
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

    @Override
    public List<Sistema> findByIdEmpresaGrupoAndNotId(Long idEmpresaGrupo, Long notId) {
        return repository.findByEmpresaGrupo_IdAndIdNot(idEmpresaGrupo, notId);
    }
}
