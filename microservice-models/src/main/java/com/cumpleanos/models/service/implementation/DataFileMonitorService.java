package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.dtos.DatafileUsage;
import com.cumpleanos.models.persistence.repository.DatafileUsageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DataFileMonitorService {

    private final DatafileUsageRepository repository;

    public List<DatafileUsage> listDatafileUsage(){
        return repository.getDatafileUsages();
    }
}
