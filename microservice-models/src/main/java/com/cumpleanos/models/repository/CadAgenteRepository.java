package com.cumpleanos.models.repository;



import com.cumpleanos.core.models.entities.CadAgente;
import com.cumpleanos.core.models.ids.CadAgenteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadAgenteRepository extends JpaRepository<CadAgente, CadAgenteId> {
}
