package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreposicionRepository extends JpaRepository<Creposicion, CreposicionId> {

    Creposicion findByReferenciaAndId_Empresa(String referencia, Long idEmpresa);

    List<Creposicion> findByTipoAndUsuarioAndFinalizadoAndEstadoNotAndFechaBetweenOrderByFechaDesc(Integer tipo, String usuario, Integer finalizado, Integer estado, LocalDate fechaAfter, LocalDate fechaBefore);

    List<Creposicion> findByTipoAndFinalizadoAndEstadoNotAndFechaBetweenOrderByFechaDesc(Integer tipo, Integer finalizado, Integer estado, LocalDate fechaAfter, LocalDate fechaBefore);
}
