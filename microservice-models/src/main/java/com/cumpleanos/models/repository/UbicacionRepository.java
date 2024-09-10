package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Ubicacion;
import com.cumpleanos.models.models.ids.UbicacionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionRepository extends JpaRepository<Ubicacion, UbicacionId> {
}
