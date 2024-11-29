package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.RolMenu;
import com.cumpleanos.core.models.entities.RolW;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolMenuRepository extends JpaRepository<RolMenu, Long> {
    List<RolMenu> findByRolWIn(List<RolW> rolWS);
}