package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Seguridad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguridadRepository extends JpaRepository<Seguridad, Long> {
}
