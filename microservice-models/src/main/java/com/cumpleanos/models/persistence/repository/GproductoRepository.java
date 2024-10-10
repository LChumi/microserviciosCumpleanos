package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Gproducto;
import com.cumpleanos.core.models.ids.GproductoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GproductoRepository extends JpaRepository<Gproducto, GproductoId> {
}
