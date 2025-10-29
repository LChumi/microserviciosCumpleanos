package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Ctipocom;
import com.cumpleanos.core.models.ids.CtipocomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CtipocomRepository extends JpaRepository<Ctipocom, CtipocomId> {
    Set<Ctipocom> findById_EmpresaAndInactivoFalseOrderByCtiId(Long empresa);
}
