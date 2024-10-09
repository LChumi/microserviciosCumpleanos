package com.cumpleanos.models.repository;


import com.cumpleanos.core.models.entities.Agente;
import com.cumpleanos.core.models.ids.AgenteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, AgenteId> {
}
