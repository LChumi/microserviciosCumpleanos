package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;
import com.cumpleanos.models.persistence.repository.DfacturaRepository;
import com.cumpleanos.models.service.interfaces.IDfacturaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DfacturaServiceImpl extends GenericServiceImpl<Dfactura, DfacturaId> implements IDfacturaService {
    private final DfacturaRepository repository;

    @Override
    public CrudRepository<Dfactura, DfacturaId> getRepository() {
        return repository;
    }

    @Override
    public ServiceResponse validateQuantities(BigInteger cco, Long producto) {
        Optional<Dfactura> dfac = repository.findByFacComprobaAndDfacProducto(cco, producto);

        if (dfac.isEmpty()) {
            return new ServiceResponse("Producto no encontrado en el detalle ", false);
        }
        return new ServiceResponse("Producto existe en el detalle procesado", true);
    }
}
