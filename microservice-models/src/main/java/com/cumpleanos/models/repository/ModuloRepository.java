package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
}
