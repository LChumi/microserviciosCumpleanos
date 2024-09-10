package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.RetDato;
import com.cumpleanos.models.models.ids.RetDatoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetDatoRepository extends JpaRepository<RetDato, RetDatoId> {
}
