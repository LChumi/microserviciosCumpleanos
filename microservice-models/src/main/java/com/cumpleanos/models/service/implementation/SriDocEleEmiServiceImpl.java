package com.cumpleanos.models.service.implementation;

import com.cumpleanos.models.persistence.repository.SriDocEleEmiRepository;
import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.core.models.ids.SriDocEleEmiId;
import com.cumpleanos.models.service.interfaces.ISriDocEleEmiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SriDocEleEmiServiceImpl extends GenericServiceImpl<SriDocEleEmi, SriDocEleEmiId> implements ISriDocEleEmiService {

    private final SriDocEleEmiRepository repository;

    @Override
    public CrudRepository<SriDocEleEmi, SriDocEleEmiId> getRepository() {
        return repository;
    }

    @Override
    public SriDocEleEmi findByClaveAcceso(String claveAcceso) {
        return repository.findByClaveAcceso(claveAcceso).orElse(null);
    }
}
