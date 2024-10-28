package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.ids.EmpleadoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, EmpleadoId> {
    Optional<Empleado> findByUsuario(Long usuarioId);
}
