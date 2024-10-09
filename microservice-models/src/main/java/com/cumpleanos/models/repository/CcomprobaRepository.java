package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Ccomproba;
import com.cumpleanos.core.models.ids.CcomprobaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcomprobaRepository extends JpaRepository<Ccomproba, CcomprobaId> {
}
