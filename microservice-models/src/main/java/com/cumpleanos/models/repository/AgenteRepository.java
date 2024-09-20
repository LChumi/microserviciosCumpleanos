package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Agente;
import core.cumpleanos.models.ids.AgenteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, AgenteId> {
}
