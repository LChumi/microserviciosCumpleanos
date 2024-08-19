package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Importacion;
import com.cumpleanos.core.models.ids.ImportacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportacionRepository extends JpaRepository<Importacion, ImportacionId> {
}