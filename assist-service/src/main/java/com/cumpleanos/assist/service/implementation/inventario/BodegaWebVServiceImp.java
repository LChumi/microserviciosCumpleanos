package com.cumpleanos.assist.service.implementation.inventario;

import com.cumpleanos.assist.persistence.repository.views.BodegaWebVRepository;
import com.cumpleanos.core.models.views.BodegaWebV;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BodegaWebVServiceImp {

    private final BodegaWebVRepository repository;

    public List<BodegaWebV> findByUsuarioAndEmpresa(Long usuario, Long empresa){
        return repository.findByUsuarioAndEmpresa(usuario, empresa);
    }

}
