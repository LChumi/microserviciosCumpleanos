package com.cumpleanos.models.repository;


import core.cumpleanos.models.entities.CadAgente;
import core.cumpleanos.models.ids.CadAgenteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadAgenteRepository extends JpaRepository<CadAgente, CadAgenteId> {
}
