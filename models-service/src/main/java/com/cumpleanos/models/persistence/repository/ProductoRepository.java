package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, ProductoId>, JpaSpecificationExecutor<Producto> {

    Optional<Producto> findById_EmpresaAndProId(Long id_empresa, String pro_id1);
}
