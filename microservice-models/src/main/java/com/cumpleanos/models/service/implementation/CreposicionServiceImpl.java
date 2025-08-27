package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import com.cumpleanos.models.persistence.repository.CreposicionRepository;
import com.cumpleanos.models.service.interfaces.ICreposicionService;
import com.cumpleanos.models.utils.enums.Sequence;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
    public ServiceResponse finalizarPedido(Long empresa, Long codigo, Long usrLiquida) {
        CreposicionId id = new CreposicionId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);
        Creposicion c = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro ningun resultado"));

        if (c != null && c.getTipo() != null && Arrays.asList(3, 4, 5).contains(c.getTipo())) {
            c.setEstado(1);
            c.setUsrLiquida(usrLiquida);

        }
    }

    @Override
    public Creposicion save(Creposicion entity) {
        Long codigo = getNextSequenceValue(Sequence.CREPOSICIONCODIGO);
1
        CreposicionId id = new CreposicionId();
        id.setCodigo(codigo);
        id.setEmpresa(entity.getId().getEmpresa());
        entity.setId(id);

        return super.save(entity);
    }
}
