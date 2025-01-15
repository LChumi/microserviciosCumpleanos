package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.PuntoVentaDTO;
import com.cumpleanos.core.models.entities.PuntoVenta;
import com.cumpleanos.core.models.ids.PuntoVentaId;
import com.cumpleanos.models.persistence.repository.PuntoVentaRepository;
import com.cumpleanos.models.service.interfaces.IPuntoVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PuntoVentaServiceImpl extends GenericServiceImpl<PuntoVenta, PuntoVentaId> implements IPuntoVentaService {
    private final PuntoVentaRepository repository;

    @Override
    public CrudRepository<PuntoVenta, PuntoVentaId> getRepository() {
        return repository;
    }

    @Override
    public Set<PuntoVentaDTO> listPuntoVentaByEmpresaAndAlmacen(Long empresa, Long almacen) {
        Set<PuntoVenta> pVentas = repository.findById_EmpresaAndId_Almacen(empresa, almacen);
        Set<PuntoVentaDTO> puntoVentas = new HashSet<>();
        for (PuntoVenta puntoVenta : pVentas) {
            puntoVentas.add(
                    new PuntoVentaDTO(
                            puntoVenta.getId().getEmpresa(),
                            puntoVenta.getId().getAlmacen(),
                            puntoVenta.getId().getSecuencia(),
                            puntoVenta.getNombre(),
                            puntoVenta.getPveId(),
                            puntoVenta.getInactivo(),
                            puntoVenta.getMultiagente(),
                            puntoVenta.getElectronico(),
                            puntoVenta.getReporte()));
        }
        return puntoVentas;
    }

    @Override
    public Optional<PuntoVentaDTO> getPuntoVentaById(PuntoVentaId puntoVentaId) {
        PuntoVenta pv = super.findById(puntoVentaId);
        if (pv == null) {
            return Optional.empty();
        }
        return Optional.of(new PuntoVentaDTO(
                pv.getId().getEmpresa(),
                pv.getId().getAlmacen(),
                pv.getId().getSecuencia(),
                pv.getNombre(),
                pv.getPveId(),
                pv.getInactivo(),
                pv.getMultiagente(),
                pv.getElectronico(),
                pv.getReporte()
        ));
    }
}
