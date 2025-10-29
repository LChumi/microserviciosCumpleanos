package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.views.StockEcommerceV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockEcommerceVRepository extends JpaRepository<StockEcommerceV, String> {

    StockEcommerceV findByProductoAndEmpresa(Long producto, Long empresa);

}
