package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.ProgramaWRepository;
import com.cumpleanos.assist.service.interfaces.IProgramaWService;
import com.cumpleanos.core.models.entities.ProgramaW;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProgramaWServiceImpl extends GenericServiceImpl<ProgramaW, Long> implements IProgramaWService {

    private final ProgramaWRepository repository;

    @Override
    public CrudRepository<ProgramaW, Long> getRepository() {
        return repository;
    }

    @Override
    public ProgramaW getProgramaByPath(String path) {
        return repository.findByPath(path).orElse(null);
    }
}