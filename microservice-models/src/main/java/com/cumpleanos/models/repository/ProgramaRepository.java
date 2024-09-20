package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
}
