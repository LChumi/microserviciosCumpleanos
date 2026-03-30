package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ImporItemDTO;
import com.cumpleanos.core.models.entities.Imporitem;
import com.cumpleanos.core.models.ids.ImporitemId;
import com.cumpleanos.models.persistence.repository.ImporItemRepository;
import com.cumpleanos.models.service.interfaces.IImporitemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImporitemServiceImpl extends GenericServiceImpl<Imporitem, ImporitemId> implements IImporitemService {

    private final ImporItemRepository repository;

    @Override
    public CrudRepository<Imporitem, ImporitemId> getRepository() {
        return repository;
    }

    @Override
    public List<ImporItemDTO> getImporItemByCco(BigInteger cco, Long codProducto) {
        List<Imporitem> items = repository.findById_IitImpComprobaAndIitProducto(cco, codProducto);
        List<ImporItemDTO> dtos = new ArrayList<>();
        for (Imporitem item : items) {

        }
        return dtos;
    }
}
