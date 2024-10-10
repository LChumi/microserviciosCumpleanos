package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.RetDato;
import com.cumpleanos.core.models.ids.RetDatoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetDatoRepository extends JpaRepository<RetDato, RetDatoId> {
}
