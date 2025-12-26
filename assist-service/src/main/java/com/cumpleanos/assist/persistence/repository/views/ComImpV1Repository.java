package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.assist.persistence.views.ComImpV1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Repository
public interface ComImpV1Repository extends JpaRepository<ComImpV1, BigInteger> {

    List<ComImpV1> findByEmpresaAndCodEstadoNotIn(Long empresa, Collection<Short> codEstados);
}
