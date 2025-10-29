package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.dtos.BodegaDTO;
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
        return BodegaDTO.builder()
                .id(bod.getId().getCodigo())
                .empresa(bod.getId().getEmpresa())
                .bodId(bod.getBodId())
                .nombre(bod.getNombre())
                .bodUbicacion(bod.getBodUbicacion())
                .almacen(bod.getAlmacenId())
                .build();
    }
}
