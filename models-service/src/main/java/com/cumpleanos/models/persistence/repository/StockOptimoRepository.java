package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.StockOptimo;
import com.cumpleanos.core.models.ids.StockOptimoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockOptimoRepository extends JpaRepository<StockOptimo, StockOptimoId> {

    StockOptimo findByProductoAndBodega(Long producto, Long bodega);
}