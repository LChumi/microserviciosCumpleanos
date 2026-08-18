package com.cumpleanos.models.persistence.repository.views;

import com.cumpleanos.core.models.views.InvProdinfgenWebV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface InvProdinfgenWebVRespository extends JpaRepository<InvProdinfgenWebV, Long> {

    InvProdinfgenWebV findByProEmpresaAndBodCodigoAndProId(Long proEmpresa, Long bodCodigo, String proId);

    List<InvProdinfgenWebV> findByProEmpresaAndBodCodigoAndProIdIn(Long proEmpresa, Long bodCodigo, Collection<String> proIds);

}