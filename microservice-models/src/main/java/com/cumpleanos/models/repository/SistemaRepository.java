package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Long> {
    Optional<Sistema> findByRuc(String ruc);
}
