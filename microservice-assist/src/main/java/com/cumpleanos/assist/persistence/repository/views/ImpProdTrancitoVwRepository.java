package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Set;

@Repository
public interface ImpProdTrancitoVwRepository extends JpaRepository<ImpProdTrancitoVw, BigInteger> {
    Set<ImpProdTrancitoVw> findByProCodigoAndEmpresa(Long proCodigo, Long empresa);
}
