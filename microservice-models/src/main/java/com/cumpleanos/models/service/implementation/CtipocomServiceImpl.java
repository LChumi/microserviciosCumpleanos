package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.dto.CtipocomDTO;
import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.ids.CtipocomId;
import com.cumpleanos.models.persistence.repository.CtipocomRepository;
import com.cumpleanos.models.service.interfaces.ICtipocomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CtipocomServiceImpl extends GenericServiceImpl<Ctipocom, CtipocomId> implements ICtipocomService {

    private final CtipocomRepository repository;

    @Override
    public CrudRepository<Ctipocom, CtipocomId> getRepository() {
        return repository;
    }

    @Override
    public Set<CtipocomDTO> listCtipocomByEmpresa(Long empresaId) {
        Set<Ctipocom> lista = repository.findById_EmpresaAndInactivoFalseOrderByCtiId(empresaId);
        Set<CtipocomDTO> dtos = new LinkedHashSet<>();
        for (Ctipocom ctipocom : lista) {
            dtos.add(new CtipocomDTO(
                    ctipocom.getId().getCodigo(),
                    ctipocom.getId().getEmpresa(),
                    ctipocom.getCtiId(),
                    ctipocom.getNombre()
            ));
        }
        return dtos;
    }
}
