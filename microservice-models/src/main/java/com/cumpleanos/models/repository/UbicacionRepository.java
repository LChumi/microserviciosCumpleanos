package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Ubicacion;
import core.cumpleanos.models.ids.UbicacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, UbicacionId> {
}
