package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.AccesoRol;
import com.cumpleanos.core.models.entities.RolW;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolWrepository extends JpaRepository<RolW, Long> {
    List<RolW> findByAccesosRol(List<AccesoRol> accesosRol);
}