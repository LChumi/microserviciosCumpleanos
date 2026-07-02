package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.PedidoHoja;
import com.cumpleanos.core.models.ids.PedidoHojaId;
import com.cumpleanos.models.persistence.repository.PedidoHojaRepository;
import com.cumpleanos.models.service.interfaces.IPedidoHojaService;
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
    public ServiceResponse updateEstadoHoja(BigInteger cco, Long estado) {
        PedidoHoja h = repository.findById_CcoComproba(cco);
        if (h == null) {
            return new ServiceResponse("No se encontró la hoja de pedido con CCO: " + cco, false);
        }
        try {
            h.setEstado(estado);
            repository.save(h);
            return new ServiceResponse("Estado actualizado correctamente", true);
        } catch (Exception e) {
            log.error("Error al actualizar el estado de la hoja de pedido: {}", e.getMessage());
            return new ServiceResponse("Error al actualizar el estado: " + e.getMessage(), false);
        }
    }

}