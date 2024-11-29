package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.core.models.ids.EmpleadoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<Empleado, EmpleadoId> {
    List<Empleado> findByUsuario(Long usuarioId);
}
