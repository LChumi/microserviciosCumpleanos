package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SistemaRepository extends JpaRepository<Sistema, Long> {
    Sistema findByRuc(String ruc);
}
