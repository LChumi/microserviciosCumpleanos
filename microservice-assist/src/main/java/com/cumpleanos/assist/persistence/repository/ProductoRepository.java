package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {
    Optional<Producto> findById_EmpresaAndProId(Long id_empresa, String pro_id1);
}
