package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.views.ImpProdTrancitoVwRepository;
import com.cumpleanos.assist.persistence.specification.ImpProdTrancitoVwSpecification;
import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import com.cumpleanos.assist.service.interfaces.IImpProdTrancitoVwService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImpProdTrancitoVwServiceImpl extends GenericServiceImpl<ImpProdTrancitoVw, BigInteger> implements IImpProdTrancitoVwService {

    private final ImpProdTrancitoVwRepository repository;

    @Override
    public CrudRepository<ImpProdTrancitoVw, BigInteger> getRepository() {
        return repository;
    }

    @Override
    public Set<ImpProdTrancitoVw> getImpProdTrancitoVwsByProdAndEmpresa(Long producto, Long empresa) {
        return repository.findByProCodigoAndEmpresa(producto, empresa);
    }

    @Override
    public Set<ImpProdTrancitoVw> find(Long empresa, String nroComprobante, String observacion, Long proveedor, LocalDate fecha, String estado) {
        Specification<ImpProdTrancitoVw> spec = ImpProdTrancitoVwSpecification.filterBy(empresa, nroComprobante, observacion, proveedor, fecha, estado);

        return new HashSet<>(repository.findAll(spec));
    }
}
