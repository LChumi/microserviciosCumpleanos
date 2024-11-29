package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.MenuW;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MenuWRepository extends JpaRepository<MenuW, Long> {
    Set<MenuW> findByReporta(Long reporta);
}