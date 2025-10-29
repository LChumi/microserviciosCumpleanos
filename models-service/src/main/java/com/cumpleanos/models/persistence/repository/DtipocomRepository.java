package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Dtipocom;
import com.cumpleanos.core.models.ids.DtipocomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DtipocomRepository extends JpaRepository<Dtipocom, DtipocomId> {
}
