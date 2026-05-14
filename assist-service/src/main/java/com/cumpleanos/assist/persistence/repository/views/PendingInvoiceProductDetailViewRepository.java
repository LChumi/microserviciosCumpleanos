package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.FacRevprodWebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public interface PendingInvoiceProductDetailViewRepository extends JpaRepository<FacRevprodWebV, String> {

    List<FacRevprodWebV> findByCcoCodigoOrderBySecuencia(BigInteger ccoCodigo);

    List<FacRevprodWebV> findByCcoCodigoIn(Collection<BigInteger> ccoCodigos);

    FacRevprodWebV findByCcoCodigoAndProCodigo(BigInteger ccoCodigo, Long proCodigo);
}