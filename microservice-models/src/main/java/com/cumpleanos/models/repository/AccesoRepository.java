package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Acceso;
import core.cumpleanos.models.ids.AccesoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, AccesoId> {
}
