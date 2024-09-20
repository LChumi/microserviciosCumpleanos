package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.PuntoVenta;
import core.cumpleanos.models.ids.PuntoVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntoVentaRepository extends JpaRepository<PuntoVenta, PuntoVentaId> {
}
