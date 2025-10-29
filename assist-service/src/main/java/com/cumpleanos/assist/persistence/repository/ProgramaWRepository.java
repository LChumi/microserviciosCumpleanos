package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.ProgramaW;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProgramaWRepository extends JpaRepository<ProgramaW, Long> {

    Optional<ProgramaW> findByPath(String path);

}
