package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Almacen;
import core.cumpleanos.models.ids.AlmacenId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, AlmacenId> {
}
