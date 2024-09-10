package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.ListaPre;
import com.cumpleanos.models.models.ids.ListaPreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaPreRepository extends JpaRepository<ListaPre, ListaPreId> {
}
