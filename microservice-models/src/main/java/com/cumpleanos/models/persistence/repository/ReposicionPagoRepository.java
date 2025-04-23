package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.ReposicionPago;
import com.cumpleanos.core.models.ids.ReposicionPagoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReposicionPagoRepository extends JpaRepository<ReposicionPago, ReposicionPagoId> {

    ReposicionPago findByCreposicionIdAndId_Empresa(Long creposicionId, Long idEmpresa);
}
