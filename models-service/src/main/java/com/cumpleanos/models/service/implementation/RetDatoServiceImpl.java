package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.RetDato;
import com.cumpleanos.core.models.ids.RetDatoId;
import com.cumpleanos.models.persistence.repository.RetDatoRepository;
import com.cumpleanos.models.service.interfaces.IRetDatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class RetDatoServiceImpl extends GenericServiceImpl<RetDato, RetDatoId> implements IRetDatoService {

    private final RetDatoRepository repository;

    @Override
    public CrudRepository<RetDato, RetDatoId> getRepository() {
        return repository;
    }

    @Override
    public RetDato getRetDatoByEmpresaTablacoaId(Long empresa, Long tablacoa, String id) {
        return repository.findById_EmpresaAndId_TablacoaAndRtdId(empresa, tablacoa, id).orElse(null);
    }
}
