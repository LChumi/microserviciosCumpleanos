package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Politica;
import com.cumpleanos.models.models.ids.PoliticaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticaRepository extends JpaRepository<Politica, PoliticaId> {
}
