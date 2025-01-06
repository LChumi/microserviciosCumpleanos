package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.AccesoRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AccesoRolRepository extends JpaRepository<AccesoRol, Long> {
    List<AccesoRol> findByUsuarioAndEmpresaOrderByOrden(Long usuario, Long empresa);
    Set<AccesoRol> findByUsuario(Long usuario);
}