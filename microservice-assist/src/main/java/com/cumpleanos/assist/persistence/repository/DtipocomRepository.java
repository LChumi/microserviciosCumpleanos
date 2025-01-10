package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.Dtipocom;
import com.cumpleanos.core.models.ids.DtipocomId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DtipocomRepository extends JpaRepository<Dtipocom, DtipocomId> {
}
