package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.AccesoDTO;
import com.cumpleanos.core.models.entities.Acceso;
import com.cumpleanos.core.models.ids.AccesoId;
import com.cumpleanos.models.persistence.repository.AccesoRepository;
import com.cumpleanos.models.service.interfaces.IAccesoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AccesoServiceImpl extends GenericServiceImpl<Acceso, AccesoId> implements IAccesoService {
    private final AccesoRepository repository;

    @Override
    public CrudRepository<Acceso, AccesoId> getRepository() {
        return repository;
    }

    @Override
    public AccesoDTO getAccesoByUsuarioAndEmpresa(Long usuario, Long empresa) {
        Acceso acceso = repository.findById_UsuarioAndId_EmpresaAndInactivoFalse(usuario, empresa)
                .orElseThrow(() -> new EntityNotFoundException("No se encontraros accesos en la empresa :"+empresa));

        return new AccesoDTO(
                acceso.getId().getUsuario(),
                acceso.getId().getEmpresa(),
                acceso.getId().getAlmacen(),
                acceso.getEmpresaDef(),
                acceso.getPuntoVenta() != null && acceso.getPuntoVenta().getId() != null
                        ? acceso.getPuntoVenta().getId().getSecuencia()
                        : null // Devuelve null si PuntoVenta o su ID son null
        );
    }
}
