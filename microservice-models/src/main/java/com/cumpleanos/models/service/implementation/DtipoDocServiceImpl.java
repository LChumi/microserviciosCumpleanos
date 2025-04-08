package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.DTipoDocDTO;
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

import java.util.HashSet;
import java.util.Set;

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
    public Set<DTipoDocDTO> getDtipodocByEmpresaAndTpdCodigo(Long empresa, Long tpdCodigo) {
        Set<DTipoDocDTO> documentos = new HashSet<>();
        Set<Dtipodoc> dtipodoc = repository.findById_EmpresaAndId_TpdCodigo(empresa, tpdCodigo);
        TipoDoc tipoDoc = tipoDocRepository.findById(tpdCodigo).
                orElseThrow(() -> new EntityNotFoundException("No se encontro datos en TipoDoc"));
        if (dtipodoc.isEmpty()) {
            return new HashSet<>();
        }
        for (Dtipodoc d : dtipodoc) {
            CtipocomId id = new CtipocomId();
            id.setCodigo(d.getId().getCtiCodigo());
            id.setEmpresa(empresa);
            Ctipocom ctipocom = ctipocomRepository.findById(id).
                    orElseThrow(() -> new EntityNotFoundException("No se encontro datos en Ctipocom"));
            documentos.add(new DTipoDocDTO(
                    empresa,
                    d.getId().getCtiCodigo(),
                    ctipocom.getNombre(),
                    ctipocom.getCtiId(),
                    tpdCodigo,
                    tipoDoc.getNombre(),
                    tipoDoc.getModulo().getModId(),
                    tipoDoc.getModulo().getId()
            ));
        }
        return documentos;
    }
}
