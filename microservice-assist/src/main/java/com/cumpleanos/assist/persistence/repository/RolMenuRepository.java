package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.RolMenu;
import com.cumpleanos.core.models.entities.RolW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolMenuRepository extends JpaRepository<RolMenu, Long> {
    List<RolMenu> findByRolWIn(List<RolW> rolWS);
}