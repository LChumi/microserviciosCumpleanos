package com.cumpleanos.models.repository;


import core.cumpleanos.models.entities.Bodega;
import core.cumpleanos.models.ids.BodegaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, BodegaId> {
}
