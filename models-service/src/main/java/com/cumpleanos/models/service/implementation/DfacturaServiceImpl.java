package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.DfacturaDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;
import com.cumpleanos.models.persistence.repository.DfacturaRepository;
import com.cumpleanos.models.service.interfaces.IDfacturaService;
import com.cumpleanos.models.utils.DtoUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static com.cumpleanos.models.utils.DtoUtils.getDfacturaDTO;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DfacturaServiceImpl extends GenericServiceImpl<Dfactura, DfacturaId> implements IDfacturaService {
    private final DfacturaRepository repository;

    @Override
    public CrudRepository<Dfactura, DfacturaId> getRepository() {
        return repository;
    }

    @Override
    public List<DfacturaDTO> getDfacturas(BigInteger cco, Long producto) {
        List<Dfactura> detalles =
                repository.findByFacComprobaAndDfacProducto(cco, producto);

        if (detalles.isEmpty()) {
            throw new EntityNotFoundException("Producto no encontrado en el detalle");
        }

        return detalles.stream()
                .map(DtoUtils::getDfacturaDTO)
                .toList();
    }

    @Override
    public ServiceResponse addCantApr(BigInteger cco, Long producto, Integer cantidad, BigDecimal precio) {

        if (cantidad == null || cantidad < 0) {
            throw new IllegalArgumentException("Cantidad inválida");
        }

        if (precio == null){
            throw new IllegalArgumentException("Precio requerido");
        }

        List<Dfactura> detalles = repository.findByFacComprobaAndDfacProducto(cco, producto);

        if (detalles.isEmpty()) {
            throw new EntityNotFoundException("Producto no encontrado en el detalle");
        }

        //Validar Lineas sin CANAPR
        List<Dfactura> disponibles = detalles.stream()
                .filter(d -> d.getCanapr() == 0)
                .toList();

        if (disponibles.isEmpty()) {
            return new ServiceResponse("No existen lineas disponibles para aprobar", false);
        }

        BigDecimal tolerancia = new BigDecimal("1.00");

        //Coincidencia exacta de precio
        Optional<Dfactura> exacto = disponibles.stream()
                .filter(d -> d.getPrecio() != null)
                .filter(d -> d.getPrecio().compareTo(precio) == 0)
                .findFirst();
        //Precio dentro del un rango
        Optional<Dfactura> enRango = disponibles.stream()
                .filter(d -> d.getPrecio() != null)
                .filter(d ->
                        d.getPrecio().subtract(precio).abs().compareTo(tolerancia) <= 0
                        )
                .findFirst();

        // Seleccion final
        Dfactura seleccionado = exacto
                .or(() -> enRango)
                .orElse(disponibles.getFirst()); //falback

        //Notificacion si el precio n o coincide
        if (seleccionado.getPrecio() != null && seleccionado.getPrecio().compareTo(precio) != 0){
            log.warn("Precio diferente asignado. cco={}, producto={}, enviado={}, usado={}",
                    cco, producto, precio, seleccionado.getPrecio());
        }

        seleccionado.setCanapr(cantidad);
        repository.save(seleccionado);

        log.info("CANAPR asignado. cco={}, producto={}, secuencia={}, precio={}",
                cco, producto, seleccionado.getPrecio(), seleccionado.getPrecio());

        return new ServiceResponse("Cantidad aprobada asignada correctamente", true);
    }
}