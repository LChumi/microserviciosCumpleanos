package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ImporItemDTO;
import com.cumpleanos.core.models.entities.Imporitem;
import com.cumpleanos.core.models.ids.ImporitemId;
import com.cumpleanos.models.persistence.repository.ImporItemRepository;
import com.cumpleanos.models.service.interfaces.IImporitemService;
import com.cumpleanos.models.utils.DtoUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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

        if (items.isEmpty()) {
            throw new EntityNotFoundException("Producto no encontrado en la importacion");
        }

        return items.stream()
                .map(DtoUtils::getImporItemDTO)
                .toList();
    }
}