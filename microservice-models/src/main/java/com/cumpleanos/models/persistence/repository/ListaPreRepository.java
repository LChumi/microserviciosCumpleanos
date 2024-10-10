package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.ListaPre;
import com.cumpleanos.core.models.ids.ListaPreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaPreRepository extends JpaRepository<ListaPre, ListaPreId> {
}
