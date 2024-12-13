package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.repository.views.ImpProdTrancitoVwRepository;
import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import com.cumpleanos.assist.service.interfaces.IImpProdTrancitoVwService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Set;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImpProdTrancitoVwServiceImpl extends  GenericServiceImpl<ImpProdTrancitoVw, BigInteger> implements IImpProdTrancitoVwService {

    private final ImpProdTrancitoVwRepository repository;

    @Override
    public CrudRepository<ImpProdTrancitoVw, BigInteger> getRepository() {
        return repository;
    }

    @Override
    public Set<ImpProdTrancitoVw> getImpProdTrancitoVwsByProdAndEmpresa(Long producto, Long empresa) {
        return repository.findByProCodigoAndEmpresa(producto, empresa);
    }
}
