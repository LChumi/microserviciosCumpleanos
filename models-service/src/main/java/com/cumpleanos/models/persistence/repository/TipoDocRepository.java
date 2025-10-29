package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.TipoDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TipoDocRepository extends JpaRepository<TipoDoc, Long> {
    Set<TipoDoc> findAllByOrderByTpdIdAsc();
}
