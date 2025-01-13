package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.DTipoDocDTO;
import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.entities.Dtipodoc;
import com.cumpleanos.core.models.entities.TipoDoc;
import com.cumpleanos.core.models.ids.CtipocomId;
import com.cumpleanos.core.models.ids.DtipodocId;
import com.cumpleanos.models.persistence.repository.CtipocomRepository;
import com.cumpleanos.models.persistence.repository.DtipodocRepository;
import com.cumpleanos.models.persistence.repository.TipoDocRepository;
import com.cumpleanos.models.service.interfaces.IDtipoDocService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DtipoDocServiceImpl extends GenericServiceImpl<Dtipodoc, DtipodocId> implements IDtipoDocService {
    private final DtipodocRepository repository;
    private final CtipocomRepository ctipocomRepository;
    private final TipoDocRepository tipoDocRepository;

    @Override
    public CrudRepository<Dtipodoc, DtipodocId> getRepository() {
        return repository;
    }

    @Override
    public DTipoDocDTO getDtipodocByEmpresaAndTpdCodigo(Long empresa, Long tpdCodigo) {
        Dtipodoc dtipodoc = repository.findById_EmpresaAndId_TpdCodigo(empresa, tpdCodigo).
                orElseThrow(() -> new EntityNotFoundException("No se encontro datos en Dtipodoc"));

        TipoDoc tipoDoc = tipoDocRepository.findById(tpdCodigo).
                orElseThrow(() -> new EntityNotFoundException("No se encontro datos en TipoDoc"));

        CtipocomId id = new CtipocomId();
        id.setCodigo(dtipodoc.getId().getCtiCodigo());
        id.setEmpresa(empresa);
        Ctipocom ctipocom = ctipocomRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("No se encontro datos en Ctipocom"));

        return new DTipoDocDTO(
                empresa,
                dtipodoc.getId().getCtiCodigo(),
                ctipocom.getNombre(),
                ctipocom.getCtiId(),
                tpdCodigo,
                tipoDoc.getNombre());
    }
}
