package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Producto;
import com.cumpleanos.models.models.ids.ProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {
}
