package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.BodegaDTO;
import com.cumpleanos.core.models.entities.Bodega;
import com.cumpleanos.core.models.ids.BodegaId;
import com.cumpleanos.models.persistence.repository.BodegaRepository;
import com.cumpleanos.models.service.interfaces.IBodegaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BodegaServiceImpl extends GenericServiceImpl<Bodega, BodegaId> implements IBodegaService {

    private final BodegaRepository repository;

    @Override
    public CrudRepository<Bodega, BodegaId> getRepository() {
        return repository;
    }

    @Override
    public BodegaDTO getBodegaWeb(Long empresa) {
        Bodega bod = repository.findById_EmpresaAndBodegaWebAndBodegaWebDef(empresa, (short) 1, (short) 1).orElseThrow(
                () -> new EntityNotFoundException("BodegaWeb no encontrada en la empresa: " + empresa)
        );
        return new BodegaDTO(
                bod.getId().getEmpresa(),
                bod.getId().getCodigo(),
                bod.getBodId(),
                bod.getNombre(),
                bod.getBodUbicacion(),
                false
        );
    }
}
