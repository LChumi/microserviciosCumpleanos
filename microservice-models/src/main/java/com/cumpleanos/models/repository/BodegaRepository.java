package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Bodega;
import com.cumpleanos.models.models.ids.BodegaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepository extends JpaRepository<Bodega, BodegaId> {
}
