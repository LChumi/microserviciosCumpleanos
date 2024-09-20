package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Total;
import core.cumpleanos.models.ids.TotalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TotalRepository extends JpaRepository<Total, TotalId> {
}
