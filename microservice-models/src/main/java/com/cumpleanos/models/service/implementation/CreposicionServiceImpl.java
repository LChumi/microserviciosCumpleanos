package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import com.cumpleanos.models.persistence.repository.CreposicionRepository;
import com.cumpleanos.models.service.interfaces.ICreposicionService;
import com.cumpleanos.models.utils.enums.Sequence;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CreposicionServiceImpl extends GenericServiceImpl<Creposicion, CreposicionId> implements ICreposicionService {

    private final CreposicionRepository repository;

    @Override
    public CrudRepository<Creposicion, CreposicionId> getRepository() {
        return repository;
    }

    @Override
    public Boolean existCreposicion(Long empresa, Long codigo) {
        CreposicionId id = new CreposicionId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);
        Creposicion c = repository.findById(id).orElse(null);
        return c != null;
    }

    @Override
    public Boolean existCreposicionByEmpresaAndReferencia(String referencia, Long empresa) {
        Creposicion c = repository.findByReferenciaAndId_Empresa(referencia, empresa);
        return c != null;
    }

    @Override
    public Creposicion save(Creposicion entity) {
        Long codigo = getNextSequenceValue(Sequence.CREPOSICIONCODIGO);

        CreposicionId id = new CreposicionId();
        id.setCodigo(codigo);
        id.setEmpresa(entity.getId().getEmpresa());
        entity.setId(id);

        return super.save(entity);
    }
}
