package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.ListaPre;
import core.cumpleanos.models.ids.ListaPreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaPreRepository extends JpaRepository<ListaPre, ListaPreId> {
}
