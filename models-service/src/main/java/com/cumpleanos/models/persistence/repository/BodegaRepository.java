package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Bodega;
import com.cumpleanos.core.models.ids.BodegaId;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BodegaRepository extends JpaRepository<Bodega, BodegaId> {

    Optional<Bodega> findById_EmpresaAndBodegaWebAndBodegaWebDef(@NotNull Long idEmpresa, Short bodegaWeb, Short bodegaWebDef);
}
