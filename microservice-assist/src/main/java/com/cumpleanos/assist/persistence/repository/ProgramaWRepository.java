package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.ProgramaW;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgramaWRepository extends JpaRepository<ProgramaW, Long> {
    Optional<ProgramaW> findByPath(String path);
}
