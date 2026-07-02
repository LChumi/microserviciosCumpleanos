package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dmovinv;
import com.cumpleanos.core.models.ids.DmovinvId;
import com.cumpleanos.models.persistence.repository.DmovinvRepository;
import com.cumpleanos.models.service.interfaces.IDmovinvSerice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class DmovinvServiceImpl extends GenericServiceImpl<Dmovinv, DmovinvId> implements IDmovinvSerice {

    private final DmovinvRepository repository;

    @Override
    public CrudRepository<Dmovinv, DmovinvId> getRepository() {
        return repository;
    }

    @Override
    public ServiceResponse actualizarCantidadDespachada(BigInteger cco, Long producto, Integer cantidad) {
        Dmovinv d = repository.findById_CmoComprobaAndProducto(cco, producto);

        if (cantidad == null || cantidad < 0) {
            return new ServiceResponse("Cantidad inválida", false);
        }

        d.setDespachada(BigDecimal.valueOf(cantidad));
        d.setFechaDespacho(LocalDateTime.now());

        try{
            repository.save(d);
            return new ServiceResponse("Cantidad actualizada correctamente", true);
        }catch(Exception e){
            return new ServiceResponse("Error al actualizar la cantidad: " + e.getMessage(), false);
        }

    }
}