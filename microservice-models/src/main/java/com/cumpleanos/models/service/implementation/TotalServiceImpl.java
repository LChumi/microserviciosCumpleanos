package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Total;
import com.cumpleanos.core.models.ids.TotalId;
import com.cumpleanos.models.persistence.repository.TotalRepository;
import com.cumpleanos.models.service.interfaces.ITotalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TotalServiceImpl extends GenericServiceImpl<Total, TotalId> implements ITotalService {
    private final TotalRepository repository;

    @Override
    public CrudRepository<Total, TotalId> getRepository() {
        return repository;
    }
}
