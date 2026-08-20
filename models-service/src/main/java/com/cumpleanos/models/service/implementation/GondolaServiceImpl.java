package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Gondola;
import com.cumpleanos.core.models.ids.GondolaId;
import com.cumpleanos.models.persistence.repository.GondolaRepository;
import com.cumpleanos.models.service.interfaces.IGondolaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GondolaServiceImpl extends GenericServiceImpl<Gondola, GondolaId> implements IGondolaService {

    private final GondolaRepository repository;


    @Override
    public CrudRepository<Gondola, GondolaId> getRepository() {
        return repository;
    }

    @Override
    public List<Gondola> finByEmpresa(Long empresa) {
        return repository.findById_Empresa(empresa);
    }

    @Override
    public List<Gondola> finByEmpresaAndUsuario(Long empresa, Long gonUsuario) {
        return repository.findById_EmpresaAndGonUsuario(empresa, gonUsuario);
    }
}
