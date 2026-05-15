package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.FacVerifiFacingWebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface PendingInvoiceHeaderViewRepository extends JpaRepository<FacVerifiFacingWebV, BigInteger> {

    List<FacVerifiFacingWebV> findAllByOrderByFechaFacDesc();

    List<FacVerifiFacingWebV> findByEmpresaCompraAndTipoCodigoOrderByFechaFacDesc(Long empresaCompra, Long tipoCodigo);

    List<FacVerifiFacingWebV> findByBodCodigo(Long bodCodigo);

}