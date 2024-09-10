package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Gproducto;
import com.cumpleanos.models.models.ids.GproductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GproductoRepository extends JpaRepository<Gproducto, GproductoId> {
}
