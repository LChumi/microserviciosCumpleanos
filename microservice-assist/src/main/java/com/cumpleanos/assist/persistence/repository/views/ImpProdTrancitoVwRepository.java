package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Set;

public interface ImpProdTrancitoVwRepository extends JpaRepository<ImpProdTrancitoVw, BigInteger> {
    Set<ImpProdTrancitoVw> findByProCodigoAndEmpresa(Long proCodigo, Long empresa);
}
