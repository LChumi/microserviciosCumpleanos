package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Imporitem;
import com.cumpleanos.core.models.ids.ImporitemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImporItemRepository extends JpaRepository<Imporitem, ImporitemId> {
}