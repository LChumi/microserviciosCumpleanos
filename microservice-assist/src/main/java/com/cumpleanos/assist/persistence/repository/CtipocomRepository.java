package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.ids.CtipocomId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CtipocomRepository extends JpaRepository<Ctipocom, CtipocomId> {
}
