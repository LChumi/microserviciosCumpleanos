package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.TipoDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocRepository extends JpaRepository<TipoDoc, Long> {
}
