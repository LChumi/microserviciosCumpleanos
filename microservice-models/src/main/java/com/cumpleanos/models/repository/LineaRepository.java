package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Linea;
import core.cumpleanos.models.ids.LineaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaRepository extends JpaRepository<Linea, LineaId> {
}
