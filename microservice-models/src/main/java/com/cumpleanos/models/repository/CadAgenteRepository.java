package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.CadAgente;
import com.cumpleanos.models.models.ids.CadAgenteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CadAgenteRepository extends JpaRepository<CadAgente, CadAgenteId> {
}
