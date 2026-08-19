package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.ProductoGondola;
import com.cumpleanos.core.models.ids.ProductoGondolaId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoGondolaRepository extends JpaRepository<ProductoGondola, ProductoGondolaId> {

    List<ProductoGondola> findById_EmpresaAndProductoInAndBodegaInAndPgoInactivoFalse(Long idEmpresa, List<Long> productos, List<Long> bodegas);
}