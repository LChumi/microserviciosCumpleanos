package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Producto;
import core.cumpleanos.models.ids.ProductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, ProductoId> {
}
