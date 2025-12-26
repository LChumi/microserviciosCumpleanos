package com.cumpleanos.assist.service.implementation.importaciones;

import com.cumpleanos.assist.persistence.repository.views.ComImpV1Repository;
import com.cumpleanos.assist.persistence.views.ComImpV1;
import com.cumpleanos.assist.service.implementation.GenericServiceImpl;
import com.cumpleanos.assist.service.interfaces.importaciones.IComImpV1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
public class ComImpV1ServiceImpl extends GenericServiceImpl<ComImpV1, BigInteger> implements IComImpV1Service {

    private final ComImpV1Repository repository;

    @Override
    public CrudRepository<ComImpV1, BigInteger> getRepository() {
        return repository;
    }

    @Override
    public List<ComImpV1> getImportaciones(Long empresa) {
        return repository.findByEmpresaAndCodEstadoNotIn(empresa, Arrays.asList((short)2, (short)3));
    }
}