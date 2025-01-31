package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.AccesoRol;
import com.cumpleanos.core.models.entities.RolW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolWrepository extends JpaRepository<RolW, Long> {
    List<RolW> findByAccesosRol(List<AccesoRol> accesosRol);
}