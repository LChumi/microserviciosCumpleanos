package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.BodegaDTO;
import com.cumpleanos.core.models.entities.Bodega;
import com.cumpleanos.core.models.entities.UsrBod;
import com.cumpleanos.core.models.ids.UsrbodId;
import com.cumpleanos.models.persistence.repository.UsrBodRepository;
import com.cumpleanos.models.service.interfaces.IUsrBodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UsrBodServiceImpl extends GenericServiceImpl<UsrBod, UsrbodId> implements IUsrBodService {
    private final UsrBodRepository repository;

    @Override
    public CrudRepository<UsrBod, UsrbodId> getRepository() {
        return repository;
    }

    @Override
    public Set<BodegaDTO> listBodByUser(Long usuario, Long empresa) {
        Set<UsrBod> lista = repository.findById_UsuarioAndId_EmpresaOrderByUboDefaultDesc(usuario, empresa);
        Set<BodegaDTO> bodegas = new LinkedHashSet<>();
        for (UsrBod usrBod : lista) {
            Bodega bod = usrBod.getBodega();
            BodegaDTO bd = new BodegaDTO(
                    bod.getId().getEmpresa(),
                    bod.getId().getCodigo(),
                    bod.getBodId(),
                    bod.getNombre(),
                    bod.getBodUbicacion(),
                    usrBod.getUboDefault()
            );
            bodegas.add(bd);
        }
        return bodegas;
    }
}
