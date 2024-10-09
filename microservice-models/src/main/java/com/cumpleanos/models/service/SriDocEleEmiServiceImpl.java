package com.cumpleanos.models.service;

import com.cumpleanos.models.repository.SriDocEleEmiRepository;
import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.core.models.ids.SriDocEleEmiId;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
