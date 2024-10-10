package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Politica;
import com.cumpleanos.core.models.ids.PoliticaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticaRepository extends JpaRepository<Politica, PoliticaId> {
}
