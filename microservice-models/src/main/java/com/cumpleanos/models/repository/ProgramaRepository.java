package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Programa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaRepository extends JpaRepository<Programa, Long> {
}
