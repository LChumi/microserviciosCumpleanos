package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SistemaRepository extends JpaRepository<Sistema, Long> {
    Optional<Sistema> findByRuc(String ruc);
}
