package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Long> {
}
