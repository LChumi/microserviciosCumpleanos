package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.RetDato;
import com.cumpleanos.core.models.ids.RetDatoId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetDatoRepository extends JpaRepository<RetDato, RetDatoId> {
    Optional<RetDato> findById_EmpresaAndId_TablacoaAndRtdId(@NotNull Long id_empresa, @NotNull Long id_tablacoa, @Size(max = 10) @NotNull String rtdId);
}
