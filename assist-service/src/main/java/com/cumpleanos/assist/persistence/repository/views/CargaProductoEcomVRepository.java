package com.cumpleanos.assist.persistence.repository.views;

import com.cumpleanos.core.models.views.CargaProductoEcomV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargaProductoEcomVRepository extends JpaRepository<CargaProductoEcomV, String> {

    CargaProductoEcomV findByProductoAndEmpresa(Long producto, Long empresa);
}
