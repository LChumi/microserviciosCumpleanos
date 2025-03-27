package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.views.CargaProductoEcomVRepository;
import com.cumpleanos.assist.service.interfaces.ICargaProductoEcomVService;
import com.cumpleanos.core.models.views.CargaProductoEcomV;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CargaProductoEcomVServiceImpl extends GenericServiceImpl<CargaProductoEcomV, String> implements ICargaProductoEcomVService {

    private final CargaProductoEcomVRepository repository;

    @Override
    public CrudRepository<CargaProductoEcomV, String> getRepository() {
        return repository;
    }

    @Override
    public CargaProductoEcomV findByProducto(Long producto, Long empresa) {
        return repository.findByProductoAndEmpresa(producto, empresa);
    }
}
