package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Politica;
import core.cumpleanos.models.ids.PoliticaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticaRepository extends JpaRepository<Politica, PoliticaId> {
}
