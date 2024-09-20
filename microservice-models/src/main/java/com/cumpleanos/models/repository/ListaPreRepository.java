package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.ListaPre;
import core.cumpleanos.models.ids.ListaPreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaPreRepository extends JpaRepository<ListaPre, ListaPreId> {
}
