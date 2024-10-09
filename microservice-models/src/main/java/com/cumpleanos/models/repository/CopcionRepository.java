package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Copcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopcionRepository extends JpaRepository<Copcion, Long> {
}
