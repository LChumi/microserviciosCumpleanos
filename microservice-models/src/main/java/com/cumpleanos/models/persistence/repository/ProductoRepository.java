package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {
}
