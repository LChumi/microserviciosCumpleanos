package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.ReposicionPago;
import com.cumpleanos.core.models.ids.ReposicionPagoId;
import com.cumpleanos.models.persistence.repository.ReposicionPagoRepository;
import com.cumpleanos.models.service.interfaces.IReposicionPagoService;
import com.cumpleanos.models.utils.enums.Sequence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReposicionPagoServiceImpl extends GenericServiceImpl<ReposicionPago, ReposicionPagoId> implements IReposicionPagoService {

    private final ReposicionPagoRepository repository;

    @Override
    public CrudRepository<ReposicionPago, ReposicionPagoId> getRepository() {
        return repository;
    }

    @Override
    public ReposicionPago getByCreposicionAndEmpresa(Long creposicion, Long empresa) {
        return repository.findByCreposicionIdAndId_Empresa(creposicion, empresa);
    }

    @Override
    public ReposicionPago save(ReposicionPago entity) {
        Long codigo = getNextSequenceValue(Sequence.REPOSICIONPAGOCODIGO);

        ReposicionPagoId id = new ReposicionPagoId();
        id.setCodigo(codigo);
        id.setEmpresa(entity.getId().getEmpresa());
        entity.setId(id);
        return super.save(entity);
    }
}
