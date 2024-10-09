package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.PuntoVenta;
import com.cumpleanos.core.models.ids.PuntoVentaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PuntoVentaRepository extends JpaRepository<PuntoVenta, PuntoVentaId> {
}
