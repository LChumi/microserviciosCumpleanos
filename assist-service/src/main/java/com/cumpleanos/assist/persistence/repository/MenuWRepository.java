package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.MenuW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MenuWRepository extends JpaRepository<MenuW, Long> {

    Set<MenuW> findByReporta(Long reporta);

    Set<MenuW> findByReportaAndInactivoFalseOrderByOrden(Long reporta);
}