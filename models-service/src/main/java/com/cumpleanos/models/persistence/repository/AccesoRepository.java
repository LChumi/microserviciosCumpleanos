package com.cumpleanos.models.persistence.repository;


import com.cumpleanos.core.models.entities.Acceso;
import com.cumpleanos.core.models.ids.AccesoId;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccesoRepository extends JpaRepository<Acceso, AccesoId> {
    Optional<Acceso> findById_UsuarioAndId_EmpresaAndInactivoFalse(@NotNull Long idUsuario, @NotNull Long idEmpresa);
}