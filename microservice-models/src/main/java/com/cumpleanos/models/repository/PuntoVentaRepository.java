package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.PuntoVenta;
import com.cumpleanos.models.models.ids.PuntoVentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuntoVentaRepository extends JpaRepository<PuntoVenta, PuntoVentaId> {
}
