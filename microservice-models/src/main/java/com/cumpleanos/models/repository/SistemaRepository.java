package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SistemaRepository extends JpaRepository<Sistema, Long> {
}
