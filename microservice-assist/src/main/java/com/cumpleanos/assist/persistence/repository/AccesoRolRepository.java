package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.AccesoRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccesoRolRepository extends JpaRepository<AccesoRol, Long> {
    List<AccesoRol> findByUsuarioAndEmpresa(Long usuario, Long empresa);
}
