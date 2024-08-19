package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ImportacionDTO;
import com.cumpleanos.core.models.entities.Importacion;
import com.cumpleanos.core.models.ids.ImportacionId;
import com.cumpleanos.models.persistence.repository.ImportacionRepository;
import com.cumpleanos.models.service.interfaces.IImportacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportacionServiceImp extends GenericServiceImpl<Importacion, ImportacionId> implements IImportacionService {

    private final ImportacionRepository repository;


    @Override
    public CrudRepository<Importacion, ImportacionId> getRepository() {
        return repository;
    }

    @Override
    public ImportacionDTO getById(BigInteger cco, Long empresa) {
        ImportacionId id = new ImportacionId();
        id.setCcoComproba(cco);
        id.setEmpresa(empresa);

        Importacion imp = repository.getReferenceById(id);

        ImportacionDTO dto = new ImportacionDTO(
                imp.getId().getCcoComproba(),
                imp.getId().getEmpresa(),
                imp.getCliente().getNombre() != null ? imp.getCliente().getNombre() : "",
                imp.getObservacion()
        );

        return null;
    }
}