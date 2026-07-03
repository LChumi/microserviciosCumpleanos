package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.PedidoHoja;
import com.cumpleanos.core.models.ids.PedidoHojaId;
import com.cumpleanos.models.persistence.repository.PedidoHojaRepository;
import com.cumpleanos.models.service.interfaces.IPedidoHojaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoHojaServiceImpl extends GenericServiceImpl<PedidoHoja, PedidoHojaId> implements IPedidoHojaService {
    private final PedidoHojaRepository repository;


    @Override
    public CrudRepository<PedidoHoja, PedidoHojaId> getRepository() {
        return repository;
    }

    @Override
    public ServiceResponse updateEstadoHoja(PedidoHojaId id, Long estado) {
        PedidoHoja h = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el pedido"));
        h.setEstado(estado);
        repository.save(h);
        return new ServiceResponse("Estado actualizado correctamente", true);
    }
}