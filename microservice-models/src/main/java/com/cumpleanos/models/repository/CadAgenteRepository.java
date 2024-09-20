package com.cumpleanos.models.repository;


import core.cumpleanos.models.entities.CadAgente;
import core.cumpleanos.models.ids.CadAgenteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadAgenteRepository extends JpaRepository<CadAgente, CadAgenteId> {
}
