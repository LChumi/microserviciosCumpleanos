package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.PuntoVenta;
import core.cumpleanos.models.ids.PuntoVentaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoVentaRepository extends JpaRepository<PuntoVenta, PuntoVentaId> {
}
