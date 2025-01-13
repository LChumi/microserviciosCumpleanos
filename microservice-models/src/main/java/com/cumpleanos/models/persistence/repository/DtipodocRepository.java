package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Dtipodoc;
import com.cumpleanos.core.models.ids.DtipodocId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DtipodocRepository extends JpaRepository<Dtipodoc, DtipodocId> {
    Optional<Dtipodoc> findById_EmpresaAndId_TpdCodigo(Long idEmpresa, Long idTpdCodigo);
}
