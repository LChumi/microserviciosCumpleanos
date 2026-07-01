package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.FacDesprodWebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ProductoDespachoRepository extends JpaRepository<FacDesprodWebV, String> {

    List<FacDesprodWebV> findByEmpresaAndCcoCodigo(Long empresa, BigInteger ccoCodigo);

    List<FacDesprodWebV> findByEmpresaAndCcoCodigoAndHoja(Long empresa, BigInteger ccoCodigo, Long hoja);

}