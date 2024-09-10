package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Agente;
import com.cumpleanos.models.models.ids.AgenteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, AgenteId> {
}
