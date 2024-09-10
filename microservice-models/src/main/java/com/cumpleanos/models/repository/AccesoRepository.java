package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Acceso;
import com.cumpleanos.models.models.ids.AccesoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccesoRepository extends JpaRepository<Acceso, AccesoId> {
}
