package com.cumpleanos.models.service.implementation;

import com.cumpleanos.core.models.entities.Ccomproba;
import com.cumpleanos.core.models.ids.CcomprobaId;
import com.cumpleanos.models.persistence.repository.CcomprobaRepository;
import com.cumpleanos.models.service.interfaces.ICcomprobaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CcomprobaServiceImpl extends GenericServiceImpl<Ccomproba, CcomprobaId> implements ICcomprobaService {
    private final CcomprobaRepository repository;

    @Override
    public CrudRepository<Ccomproba, CcomprobaId> getRepository() {
        return repository;
    }

    @Override
    public Boolean updateBodegaCco(Long empresa, BigInteger codigo, Long bodega) {
        CcomprobaId id = new CcomprobaId();
        id.setCodigo(codigo);
        id.setEmpresa(empresa);

        Ccomproba c = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro el comprobante "));
        c.setCcoBodega(bodega);
        repository.save(c);
        return true;
    }
}
