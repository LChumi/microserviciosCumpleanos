package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.ProductoPartida;
import com.cumpleanos.core.models.ids.ProductoPartidaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoPartidaRepository extends JpaRepository<ProductoPartida, ProductoPartidaId> {

    ProductoPartida getById_ProductoAndId_Empresa(Long idProducto, Long idEmpresa);

}
