package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Gproducto;
import core.cumpleanos.models.ids.GproductoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GproductoRepository extends JpaRepository<Gproducto, GproductoId> {
}
